package com.example.oms.mapper;

import com.example.oms.dto.OrderItemDto;
import com.example.oms.dto.OrderResponseDto;
import com.example.oms.model.Order;
import com.example.oms.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.email", target = "customerEmail")
    OrderResponseDto toDto(Order order);
    
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemDto toOrderItemDto(OrderItem orderItem);
}