package com.example.oms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    
    @NotNull(message = "ID покупателя не может быть пустым")
    private Long customerId;
    
    @NotEmpty(message = "Заказ должен содержать хотя бы один товар")
    @Valid
    private List<OrderItemRequest> items;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {
        
        @NotNull(message = "ID продукта не может быть пустым")
        private Long productId;
        
        @NotNull(message = "Количество не может быть пустым")
        @jakarta.validation.constraints.Min(value = 1, message = "Количество должно быть не менее 1")
        private Integer quantity;
    }
}