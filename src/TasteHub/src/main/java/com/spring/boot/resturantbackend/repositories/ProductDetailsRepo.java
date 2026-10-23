package com.spring.boot.resturantbackend.repositories;

import com.spring.boot.resturantbackend.models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {
    Optional<ProductDetails> findByProductId(Long productId);
}
