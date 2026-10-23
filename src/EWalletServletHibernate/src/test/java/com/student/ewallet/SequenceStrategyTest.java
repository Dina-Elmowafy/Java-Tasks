package com.student.ewallet;

import com.student.ewallet.model.TransactionType;
import com.student.ewallet.model.WalletTransaction;
import com.student.ewallet.util.JpaUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SequenceStrategyTest {
    @Test
    void sequenceStartsAt1000AndIncreasesByOne() {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            WalletTransaction first = new WalletTransaction(
                    "WTEST1", TransactionType.DEPOSIT, new BigDecimal("50.00"), null);
            WalletTransaction second = new WalletTransaction(
                    "WTEST1", TransactionType.WITHDRAW, new BigDecimal("10.00"), null);
            em.persist(first);
            em.persist(second);
            em.getTransaction().commit();

            assertEquals(1000L, first.getId());
            assertEquals(1001L, second.getId());
        } finally {
            em.close();
            JpaUtil.close();
        }
    }
}
