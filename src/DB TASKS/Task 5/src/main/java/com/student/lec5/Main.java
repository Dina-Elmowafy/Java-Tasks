package com.student.lec5;

import com.student.lec5.entity.Course;
import com.student.lec5.entity.Doctor;
import com.student.lec5.entity.Employee;
import com.student.lec5.entity.Language;
import com.student.lec5.entity.Patient;
import com.student.lec5.entity.Phone;
import com.student.lec5.entity.Student;
import com.student.lec5.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("lecture5-unit");

        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Course database = new Course("Database");
            Course java = new Course("Java");

            Student ahmed = new Student("Ahmed Ali", "ahmed@example.com");
            Student mona = new Student("Mona Hassan", "mona@example.com");

            ahmed.addCourse(database);
            ahmed.addCourse(java);
            mona.addCourse(database);

            // Course does not have cascade persist, so save courses first.
            entityManager.persist(database);
            entityManager.persist(java);
            entityManager.persist(ahmed);
            entityManager.persist(mona);

            // Task 2: Doctor and Patient (Many-to-Many).
            Doctor doctor = new Doctor("Dr. Ali", 15000);
            Patient patient = new Patient("Omar", 25);
            doctor.addPatient(patient);
            entityManager.persist(doctor);

            // Task 3: Many teachers can use the same language.
            Language english = new Language("English");
            Teacher teacher1 = new Teacher("Sara", 9000, english);
            Teacher teacher2 = new Teacher("Mona", 8500, english);
            english.addTeacher(teacher1);
            english.addTeacher(teacher2);
            entityManager.persist(english);

            // Task 4: Employee and Phone (One-to-One).
            Employee employee = new Employee("Ahmed", 28);
            Phone phone = new Phone("01012345678");
            employee.assignPhone(phone);
            entityManager.persist(employee);

            entityManager.getTransaction().commit();

            System.out.println("Students and courses saved successfully.");
            System.out.println("Ahmed courses count: " + ahmed.getCourses().size());
            System.out.println("Mona courses count: " + mona.getCourses().size());
            System.out.println("Check STUDENT_COURSES table in Oracle.");
            System.out.println("All seven lecture tasks were applied.");
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            entityManager.close();
            factory.close();
        }
    }
}
