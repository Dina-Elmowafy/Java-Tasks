package com.student.university.controller;

import com.student.university.dto.UniversityDtos.InstructorDetails;
import com.student.university.model.Instructor;
import com.student.university.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final UniversityService service;

    public InstructorController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createInstructor(instructor));
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAll() {
        return ResponseEntity.ok(service.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInstructorDetails(id));
    }
}
