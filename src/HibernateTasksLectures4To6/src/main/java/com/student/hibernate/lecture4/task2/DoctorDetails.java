package com.student.hibernate.lecture4.task2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "l4_doctor_details")
public class DoctorDetails {

    @Id
    private Long id;
    private String fullAddress;
    private String firstName;
    private String lastName;
    private Integer age;

    public DoctorDetails() {
    }

    public DoctorDetails(Long id, String fullAddress, String firstName,
                         String lastName, Integer age) {
        this.id = id;
        this.fullAddress = fullAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}

