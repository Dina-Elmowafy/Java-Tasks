package com.student.lec5.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @SequenceGenerator(name = "language_sequence", sequenceName = "language_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_sequence")
    @Column(name = "language_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new HashSet<>();

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setLanguage(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }
}

