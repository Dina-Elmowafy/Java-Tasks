package com.student.hibernate.lecture5;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "L5Student")
@Table(name = "l5_students")
public class Student {

    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}

