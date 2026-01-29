package com.example.oms.mapper;

import com.example.oms.dto.CreateCustomerRequest;
import com.example.oms.dto.CustomerDto;
import com.example.oms.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T19:13:46+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setName( customer.getName() );

        return customerDto;
    }

    @Override
    public Customer toEntity(CreateCustomerRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setEmail( request.getEmail() );
        customer.setName( request.getName() );

        return customer;
    }
}
