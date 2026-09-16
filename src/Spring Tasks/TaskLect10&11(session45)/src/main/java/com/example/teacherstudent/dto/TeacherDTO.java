package com.example.teacherstudent.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {
    private Long id;
    private String name;
    private List<StudentDTO> students;

    public TeacherDTO() {
    }

    public TeacherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeacherDTO(Long id, String name, List<StudentDTO> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<StudentDTO> getStudents() { return students; }
    public void setStudents(List<StudentDTO> students) { this.students = students; }
}
