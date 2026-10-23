package com.student.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "teachers")
@Check(constraints = "age between 15 and 20")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private Long id;

    @Size(max = 50)
    @Column(name = "teacher_name", length = 50)
    private String name;

    @Min(15)
    @Max(20)
    @Column(name = "age")
    private Integer age;

    @Column(name = "address", unique = true)
    private String address;

    public Teacher() {
    }

    public Teacher(Long id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

