package com.student.hibernate.lecture4.task2;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "l4_hospitals")
public class Hospital {

    @Id
    private Long id;
    private String name;
    private Integer numberOfDoctors;
    private Integer numberOfPatients;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "l4_hospital_patients",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients = new ArrayList<>();

    public Hospital() {
    }

    public Hospital(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        numberOfDoctors = doctors.size();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        numberOfPatients = patients.size();
    }
}

