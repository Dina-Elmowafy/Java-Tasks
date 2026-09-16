package com.example.teacherstudent.controller;

import com.example.teacherstudent.dto.StudentDTO;
import com.example.teacherstudent.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) { this.studentService = studentService; }
    @GetMapping public List<StudentDTO> getAll() { return studentService.getAll(); }
    @GetMapping("/{id}") public StudentDTO getById(@PathVariable Long id) { return studentService.getById(id); }
}
