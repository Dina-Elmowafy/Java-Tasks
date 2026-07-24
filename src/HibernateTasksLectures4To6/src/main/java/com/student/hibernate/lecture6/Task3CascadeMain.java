package com.student.hibernate.lecture6;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Task3CascadeMain {

    public static void main(String[] args) {
        User user = new User(2L, "Mona", 20);
        user.setUserDetails(
                new UserDetails(2L, "Alexandria", "01111111111")
        );
        user.addFriend(new Friend(2L, "Sara"));
        user.addPost(new Post(2L, "Hibernate", "Cascade example"));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Cascade saves the related objects with the user.
            session.persist(user);

            transaction.commit();
        }

        HibernateUtil.close();
    }
}

