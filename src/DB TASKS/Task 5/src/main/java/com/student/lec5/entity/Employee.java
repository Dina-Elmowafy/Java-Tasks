package com.student.lec5.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees_lec5")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_l5_sequence", sequenceName = "employee_l5_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_l5_sequence")
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "phone_id", nullable = false, unique = true)
    private Phone phone;

    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void assignPhone(Phone phone) {
        this.phone = phone;
        phone.setEmployee(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Phone getPhone() {
        return phone;
    }
}

