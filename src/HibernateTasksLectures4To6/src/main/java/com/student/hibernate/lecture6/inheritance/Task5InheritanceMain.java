package com.student.hibernate.lecture6.inheritance;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Task5InheritanceMain {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(new AdminUser(1L, "Admin", "ALL"));
            session.persist(new NormalUser(2L, "Ahmed", 100));

            transaction.commit();
        }

        HibernateUtil.close();
    }
}
