package com.student.hibernate.lecture4.task2;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "l4_doctors")
public class Doctor {

    @Id
    private Long id;
    private String userName;
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    private DoctorDetails doctorDetails;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(Long id, String userName, Double salary) {
        this.id = id;
        this.userName = userName;
        this.salary = salary;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.setDoctor(this);
    }
}

