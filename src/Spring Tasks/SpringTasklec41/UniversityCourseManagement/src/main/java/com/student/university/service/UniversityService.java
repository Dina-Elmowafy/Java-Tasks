package com.student.university.service;

import com.student.university.dto.UniversityDtos.*;
import com.student.university.model.Course;
import com.student.university.model.Instructor;
import com.student.university.model.Student;
import com.student.university.repository.CourseRepository;
import com.student.university.repository.InstructorRepository;
import com.student.university.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public UniversityService(StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             InstructorRepository instructorRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Transactional
    public Student registerStudent(Long studentId, Long courseId) {
        Student student = getStudent(studentId);
        Course course = getCourse(courseId);
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    @Transactional
    public Course assignInstructor(Long courseId, Long instructorId) {
        Course course = getCourse(courseId);
        Instructor instructor = getInstructor(instructorId);
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public StudentDetails getStudentDetails(Long id) {
        Student student = getStudent(id);
        List<CourseInfo> courses = student.getCourses().stream()
                .map(course -> new CourseInfo(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        toInstructorInfo(course.getInstructor())))
                .toList();

        return new StudentDetails(student.getId(), student.getName(), student.getEmail(), courses);
    }

    @Transactional(readOnly = true)
    public CourseDetails getCourseDetails(Long id) {
        Course course = getCourse(id);
        List<StudentInfo> students = course.getStudents().stream()
                .map(this::toStudentInfo)
                .toList();

        return new CourseDetails(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                toInstructorInfo(course.getInstructor()),
                students);
    }

    @Transactional(readOnly = true)
    public InstructorDetails getInstructorDetails(Long id) {
        Instructor instructor = getInstructor(id);
        List<CourseWithStudents> courses = instructor.getCourses().stream()
                .map(course -> new CourseWithStudents(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getStudents().stream().map(this::toStudentInfo).toList()))
                .toList();

        return new InstructorDetails(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                courses);
    }

    private Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    private Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    private Instructor getInstructor(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
    }

    private StudentInfo toStudentInfo(Student student) {
        return new StudentInfo(student.getId(), student.getName(), student.getEmail());
    }

    private InstructorInfo toInstructorInfo(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        return new InstructorInfo(instructor.getId(), instructor.getName(), instructor.getEmail());
    }
}
