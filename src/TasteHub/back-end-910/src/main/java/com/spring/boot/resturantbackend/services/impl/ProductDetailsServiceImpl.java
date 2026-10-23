package com.spring.boot.resturantbackend.services.impl;

import com.spring.boot.resturantbackend.dto.ProductDetailsDto;
import com.spring.boot.resturantbackend.mappers.ProductDetailsMapper;
import com.spring.boot.resturantbackend.models.Product;
import com.spring.boot.resturantbackend.models.ProductDetails;
import com.spring.boot.resturantbackend.repositories.ProductDetailsRepo;
import com.spring.boot.resturantbackend.repositories.ProductRepo;
import com.spring.boot.resturantbackend.services.ProductDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;

@Service
@Transactional
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private final ProductDetailsRepo detailsRepo;
    private final ProductRepo productRepo;

    public ProductDetailsServiceImpl(ProductDetailsRepo detailsRepo, ProductRepo productRepo) {
        this.detailsRepo = detailsRepo;
        this.productRepo = productRepo;
    }

    @Override
    @CacheEvict(value = {"productDetails", "products"}, allEntries = true)
    public ProductDetailsDto add(ProductDetailsDto dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("products.not.found"));
        if (detailsRepo.findByProductId(product.getId()).isPresent()) {
            throw new IllegalArgumentException("product.details.already.exists");
        }
        ProductDetails details = ProductDetailsMapper.INSTANCE.toEntity(dto);
        details.setProduct(product);
        return ProductDetailsMapper.INSTANCE.toDto(detailsRepo.save(details));
    }

    @Override
    @CacheEvict(value = {"productDetails", "products"}, allEntries = true)
    public ProductDetailsDto update(Long id, ProductDetailsDto dto) {
        ProductDetails details = detailsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("product.details.not.found"));
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("products.not.found"));
        detailsRepo.findByProductId(product.getId()).filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> { throw new IllegalArgumentException("product.details.already.exists"); });
        ProductDetailsMapper.INSTANCE.update(dto, details);
        details.setProduct(product);
        return ProductDetailsMapper.INSTANCE.toDto(detailsRepo.save(details));
    }

    @Override
    @CacheEvict(value = "productDetails", allEntries = true)
    public void delete(Long id) {
        if (!detailsRepo.existsById(id)) {
            throw new IllegalArgumentException("product.details.not.found");
        }
        detailsRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @org.springframework.cache.annotation.Cacheable(value = "productDetails", key = "#productId")
    public ProductDetailsDto getByProductId(Long productId) {
        return detailsRepo.findByProductId(productId).map(ProductDetailsMapper.INSTANCE::toDto)
                .orElseThrow(() -> new IllegalArgumentException("product.details.not.found"));
    }
}
