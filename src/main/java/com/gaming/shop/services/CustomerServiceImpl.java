package com.gaming.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.shop.models.dtos.CustomerDTO;
import com.gaming.shop.models.entities.Customer;
import com.gaming.shop.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final ObjectMapper objectMapper;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ObjectMapper objectMapper,
                               CustomerRepository customerRepository) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer " + savedCustomer.getFirstName() + " was created!");
        return objectMapper.convertValue(savedCustomer, CustomerDTO.class);
    }
}
