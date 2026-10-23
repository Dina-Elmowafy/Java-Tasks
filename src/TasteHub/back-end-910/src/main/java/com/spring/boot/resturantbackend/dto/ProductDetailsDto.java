package com.spring.boot.resturantbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {
    private Long id;
    @NotNull(message = "product.id.required")
    private Long productId;
    @NotBlank(message = "product.details.required")
    @Size(max = 2000, message = "product.details.size")
    private String details;
    @Size(max = 2000, message = "product.ingredients.size")
    private String ingredients;
}
