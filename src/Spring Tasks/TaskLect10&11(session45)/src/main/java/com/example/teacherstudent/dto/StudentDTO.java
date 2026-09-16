package com.example.teacherstudent.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Long id;
    private String name;
    private List<TeacherDTO> teachers;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudentDTO(Long id, String name, List<TeacherDTO> teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<TeacherDTO> getTeachers() { return teachers; }
    public void setTeachers(List<TeacherDTO> teachers) { this.teachers = teachers; }
}
