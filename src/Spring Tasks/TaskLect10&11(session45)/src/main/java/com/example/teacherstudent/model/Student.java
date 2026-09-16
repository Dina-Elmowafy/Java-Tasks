package com.example.teacherstudent.model;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Teacher> getTeachers() { return teachers; }
}
