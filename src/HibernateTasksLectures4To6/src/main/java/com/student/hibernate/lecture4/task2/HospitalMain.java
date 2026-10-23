package com.student.hibernate.lecture4.task2;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HospitalMain {

    public static void main(String[] args) {
        Hospital hospital = new Hospital(1L, "City Hospital");
        Doctor doctor = new Doctor(1L, "doctor_ahmed", 12000.0);
        DoctorDetails details =
                new DoctorDetails(1L, "Cairo", "Ahmed", "Ali", 35);
        Patient patient = new Patient(1L, "Mona", "Flu");

        doctor.setDoctorDetails(details);
        doctor.addPatient(patient);
        hospital.addDoctor(doctor);
        hospital.addPatient(patient);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(hospital);
            transaction.commit();
        }

        HibernateUtil.close();
    }
}
