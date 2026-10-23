package com.spring.boot.resturantbackend.services;

import com.spring.boot.resturantbackend.dto.ProductDetailsDto;

public interface ProductDetailsService {
    ProductDetailsDto add(ProductDetailsDto dto);
    ProductDetailsDto update(Long id, ProductDetailsDto dto);
    void delete(Long id);
    ProductDetailsDto getByProductId(Long productId);
}
