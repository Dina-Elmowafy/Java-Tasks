package com.student.hibernate;

import com.student.hibernate.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Teacher teacher = new Teacher(1L, "Ahmed Ali", 18, "Cairo");
        session.persist(teacher);

        transaction.commit();
        session.close();
        sessionFactory.close();

        System.out.println("Teacher saved successfully");
    }
}

