package com.example.oms.mapper;

import com.example.oms.dto.CreateProductRequest;
import com.example.oms.dto.ProductDto;
import com.example.oms.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    
    ProductDto toDto(Product product);
    
    Product toEntity(CreateProductRequest request);
}