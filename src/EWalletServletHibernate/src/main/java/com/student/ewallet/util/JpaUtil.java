package com.student.ewallet.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("walletPU");

    private JpaUtil() {}

    public static EntityManager createEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        if (FACTORY.isOpen()) FACTORY.close();
    }
}
