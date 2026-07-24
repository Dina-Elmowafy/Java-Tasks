package com.student.hibernate.lecture6;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Task2WithoutCascadeMain {

    public static void main(String[] args) {
        User user = new User(1L, "Ahmed", 22);
        UserDetails details = new UserDetails(1L, "Cairo", "01000000000");
        Friend friend = new Friend(1L, "Ali");
        Post post = new Post(1L, "First Post", "Hello");

        user.setUserDetails(details);
        user.addFriend(friend);
        user.addPost(post);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Every object is saved manually.
            session.persist(details);
            session.persist(friend);
            session.persist(user);
            session.persist(post);

            transaction.commit();
        }

        HibernateUtil.close();
    }
}

