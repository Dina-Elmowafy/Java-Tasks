package com.student.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence_generator",
            sequenceName = "EMPLOYEE_SEQ",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence_generator"
    )
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must not exceed 100")
    @Column(name = "AGE", nullable = false)
    private Integer age;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+ -]{7,20}$", message = "Phone number is not valid")
    @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
    private String phoneNumber;

    public Employee() {
    }

    public Employee(String name, Integer age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
