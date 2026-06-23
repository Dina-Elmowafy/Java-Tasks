package com.example;

import com.example.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        Teacher teacher = new Teacher(1, "Ahmed Ali", 18, "Mansoura");

        session.save(teacher);

        transaction.commit();

        session.close();
        factory.close();

        System.out.println("Teacher saved successfully");
    }
}