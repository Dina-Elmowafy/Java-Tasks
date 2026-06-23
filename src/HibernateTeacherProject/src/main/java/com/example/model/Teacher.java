package com.example.model;

import org.hibernate.annotations.Check;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "TEACHER")
@Check(constraints = "AGE BETWEEN 15 AND 20")
public class Teacher {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Min(15)
    @Max(20)
    @Column(name = "AGE")
    private int age;

    @Column(name = "ADDRESS", unique = true)
    private String address;

    public Teacher() {
    }

    public Teacher(int id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    } 

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}