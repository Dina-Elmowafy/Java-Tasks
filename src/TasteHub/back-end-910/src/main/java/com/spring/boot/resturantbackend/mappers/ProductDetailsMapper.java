package com.spring.boot.resturantbackend.mappers;

import com.spring.boot.resturantbackend.dto.ProductDetailsDto;
import com.spring.boot.resturantbackend.models.ProductDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailsMapper {
    ProductDetailsMapper INSTANCE = Mappers.getMapper(ProductDetailsMapper.class);

    @Mapping(target = "productId", source = "product.id")
    ProductDetailsDto toDto(ProductDetails details);

    @Mapping(target = "product", ignore = true)
    ProductDetails toEntity(ProductDetailsDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    void update(ProductDetailsDto dto, @MappingTarget ProductDetails details);
}
