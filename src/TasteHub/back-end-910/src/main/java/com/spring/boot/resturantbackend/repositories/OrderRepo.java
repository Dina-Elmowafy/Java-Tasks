package com.spring.boot.resturantbackend.repositories;

import com.spring.boot.resturantbackend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Page<Order> findByAccountIdOrderByIdAsc(Long accountId, Pageable pageable);
    Page<Order> findAllByOrderByIdAsc(Pageable pageable);
}
