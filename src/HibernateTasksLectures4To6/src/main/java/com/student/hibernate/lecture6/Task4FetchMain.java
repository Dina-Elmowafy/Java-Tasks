package com.student.hibernate.lecture6;

import com.student.hibernate.HibernateUtil;
import com.student.hibernate.lecture6.eager.EagerUser;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class Task4FetchMain {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, 1L);

            System.out.println(
                    "Details loaded before use: "
                            + Hibernate.isInitialized(user.getUserDetails())
            );

            Hibernate.initialize(user.getUserDetails());

            System.out.println(
                    "Details loaded after use: "
                            + Hibernate.isInitialized(user.getUserDetails())
            );

            EagerUser eagerUser = session.get(EagerUser.class, 1L);
            System.out.println(
                    "Eager details loaded: "
                            + Hibernate.isInitialized(eagerUser.getUserDetails())
            );
        }

        HibernateUtil.close();
    }
}
