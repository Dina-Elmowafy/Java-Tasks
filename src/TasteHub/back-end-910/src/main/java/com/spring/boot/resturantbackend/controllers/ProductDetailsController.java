package com.spring.boot.resturantbackend.controllers;

import com.spring.boot.resturantbackend.dto.ProductDetailsDto;
import com.spring.boot.resturantbackend.services.ProductDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-details")
public class ProductDetailsController {
    private final ProductDetailsService service;

    public ProductDetailsController(ProductDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDetailsDto> add(@Valid @RequestBody ProductDetailsDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> update(@PathVariable Long id, @Valid @RequestBody ProductDetailsDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<ProductDetailsDto> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getByProductId(productId));
    }
}
