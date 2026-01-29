package com.example.oms.service;

import com.example.oms.dto.CreateCustomerRequest;
import com.example.oms.dto.CustomerDto;
import com.example.oms.mapper.CustomerMapper;
import com.example.oms.model.Customer;
import com.example.oms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    @Transactional
    public CustomerDto createCustomer(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Покупатель с email " + request.getEmail() + " уже существует");
        }
        
        Customer customer = customerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }
    
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель с ID " + id + " не найден"));
        return customerMapper.toDto(customer);
    }
    
    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель с email " + email + " не найден"));
        return customerMapper.toDto(customer);
    }
    
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Покупатель с ID " + id + " не найден");
        }
        customerRepository.deleteById(id);
    }
}