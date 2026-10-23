package com.example.teacherstudent.controller;

import com.example.teacherstudent.dto.TeacherDTO;
import com.example.teacherstudent.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService; }
    @GetMapping public List<TeacherDTO> getAll() { return teacherService.getAll(); }
    @GetMapping("/{id}") public TeacherDTO getById(@PathVariable Long id) { return teacherService.getById(id); }
}
