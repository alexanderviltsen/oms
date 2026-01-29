package com.example.oms.service;

import com.example.oms.dto.CreateOrderRequest;
import com.example.oms.dto.OrderResponseDto;
import com.example.oms.mapper.OrderMapper;
import com.example.oms.model.*;
import com.example.oms.repository.CustomerRepository;
import com.example.oms.repository.OrderRepository;
import com.example.oms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    
    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequest request) {
        // Проверяем существование покупателя
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Покупатель с ID " + request.getCustomerId() + " не найден"));
        
        // Создаем заказ
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderItems(new ArrayList<>());
        
        // Добавляем товары в заказ
        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Продукт с ID " + itemRequest.getProductId() + " не найден"));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            
            order.getOrderItems().add(orderItem);
        }
        
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }
    
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(() -> new IllegalArgumentException("Заказ с ID " + id + " не найден"));
        return orderMapper.toDto(order);
    }
    
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<OrderResponseDto> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<OrderResponseDto> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public OrderResponseDto updateOrderStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Заказ с ID " + id + " не найден"));
        
        order.setStatus(newStatus);
        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }
    
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Заказ с ID " + id + " не найден");
        }
        orderRepository.deleteById(id);
    }
}