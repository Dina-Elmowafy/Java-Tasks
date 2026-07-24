package com.student.hibernate.lecture5;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Lecture5Main {

    public static void main(String[] args) {
        saveFromCourse();
        saveFromStudent();
        HibernateUtil.close();
    }

    private static void saveFromCourse() {
        Course course = new Course(1L, "Java");
        Student student = new Student(1L, "Ahmed");
        course.addStudent(student);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        }
    }

    private static void saveFromStudent() {
        Course course = new Course(2L, "Hibernate");
        Student student = new Student(2L, "Mona");
        course.addStudent(student);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
    }
}
