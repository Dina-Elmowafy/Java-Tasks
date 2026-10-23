package com.example.teacherstudent.service.impl;

import com.example.teacherstudent.dto.StudentDTO;
import com.example.teacherstudent.dto.TeacherDTO;
import com.example.teacherstudent.exception.ResourceNotFoundException;
import com.example.teacherstudent.model.Student;
import com.example.teacherstudent.repository.StudentRepository;
import com.example.teacherstudent.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) { this.studentRepository = studentRepository; }
    @Override public List<StudentDTO> getAll() { return studentRepository.findAllWithTeachers().stream().map(this::toDTO).toList(); }
    @Override public StudentDTO getById(Long id) {
        Student student = studentRepository.findByIdWithTeachers(id).orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " was not found"));
        return toDTO(student);
    }
    private StudentDTO toDTO(Student student) {
        List<TeacherDTO> teachers = student.getTeachers().stream().map(teacher -> new TeacherDTO(teacher.getId(), teacher.getName())).toList();
        return new StudentDTO(student.getId(), student.getName(), teachers);
    }
}
