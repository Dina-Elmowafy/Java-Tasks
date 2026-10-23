package com.student.university.controller;

import com.student.university.dto.UniversityDtos.CourseDetails;
import com.student.university.model.Course;
import com.student.university.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final UniversityService service;

    public CourseController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseDetails(id));
    }

    @PutMapping("/{courseId}/instructors/{instructorId}")
    public ResponseEntity<Course> assignInstructor(@PathVariable Long courseId,
                                                   @PathVariable Long instructorId) {
        return ResponseEntity.ok(service.assignInstructor(courseId, instructorId));
    }
}
