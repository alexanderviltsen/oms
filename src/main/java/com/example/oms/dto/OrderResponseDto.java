package com.example.oms.dto;

import com.example.oms.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private List<OrderItemDto> orderItems;
}