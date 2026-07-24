package com.student.hibernate.lecture4.task2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "l4_patients")
public class Patient {

    @Id
    private Long id;
    private String name;
    private String typeOfDisease;

    @ManyToOne
    private Doctor doctor;

    public Patient() {
    }

    public Patient(Long id, String name, String typeOfDisease) {
        this.id = id;
        this.name = name;
        this.typeOfDisease = typeOfDisease;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}

