package com.student.hibernate.lecture5;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "L5Course")
@Table(name = "l5_courses")
public class Course {

    @Id
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "l5_course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }
}

