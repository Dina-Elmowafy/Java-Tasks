package com.student.university.controller;

import com.student.university.dto.UniversityDtos.StudentDetails;
import com.student.university.model.Student;
import com.student.university.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final UniversityService service;

    public StudentController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentDetails(id));
    }

    @PutMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> registerInCourse(@PathVariable Long studentId,
                                                    @PathVariable Long courseId) {
        return ResponseEntity.ok(service.registerStudent(studentId, courseId));
    }
}
