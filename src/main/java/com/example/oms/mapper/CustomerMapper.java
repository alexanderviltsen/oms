package com.example.oms.mapper;

import com.example.oms.dto.CreateCustomerRequest;
import com.example.oms.dto.CustomerDto;
import com.example.oms.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    
    CustomerDto toDto(Customer customer);
    
    Customer toEntity(CreateCustomerRequest request);
}