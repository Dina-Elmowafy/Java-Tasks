package com.student.hibernate.lecture4.task1;

import com.student.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerMain {

    public static void main(String[] args) {
        savePlayer();
        getPlayer();
        updatePlayer();
        deletePlayer();
        HibernateUtil.close();
    }

    private static void savePlayer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new Player(1L, "Ahmed", "18", true));
            transaction.commit();
        }
    }

    private static void getPlayer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Player player = session.get(Player.class, 1L);
            System.out.println(player.getName());
        }
    }

    private static void updatePlayer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Player player = session.get(Player.class, 1L);
            player.setName("Ahmed Ali");
            transaction.commit();
        }
    }

    private static void deletePlayer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Player player = session.get(Player.class, 1L);
            session.remove(player);
            transaction.commit();
        }
    }
}

