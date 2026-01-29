package com.example.oms.mapper;

import com.example.oms.dto.OrderItemDto;
import com.example.oms.dto.OrderResponseDto;
import com.example.oms.model.Customer;
import com.example.oms.model.Order;
import com.example.oms.model.OrderItem;
import com.example.oms.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T19:13:45+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponseDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setCustomerId( orderCustomerId( order ) );
        orderResponseDto.setCustomerName( orderCustomerName( order ) );
        orderResponseDto.setCustomerEmail( orderCustomerEmail( order ) );
        orderResponseDto.setId( order.getId() );
        orderResponseDto.setCreatedAt( order.getCreatedAt() );
        orderResponseDto.setStatus( order.getStatus() );
        orderResponseDto.setOrderItems( orderItemListToOrderItemDtoList( order.getOrderItems() ) );

        return orderResponseDto;
    }

    @Override
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setProductId( orderItemProductId( orderItem ) );
        orderItemDto.setProductName( orderItemProductName( orderItem ) );
        orderItemDto.setId( orderItem.getId() );
        orderItemDto.setQuantity( orderItem.getQuantity() );
        orderItemDto.setPrice( orderItem.getPrice() );

        return orderItemDto;
    }

    private Long orderCustomerId(Order order) {
        if ( order == null ) {
            return null;
        }
        Customer customer = order.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderCustomerName(Order order) {
        if ( order == null ) {
            return null;
        }
        Customer customer = order.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String name = customer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String orderCustomerEmail(Order order) {
        if ( order == null ) {
            return null;
        }
        Customer customer = order.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String email = customer.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( toOrderItemDto( orderItem ) );
        }

        return list1;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderItemProductName(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
