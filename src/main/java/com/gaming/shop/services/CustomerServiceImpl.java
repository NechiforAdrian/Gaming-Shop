package com.gaming.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.shop.exception.CustomerNotFoundException;
import com.gaming.shop.models.dtos.CustomerDTO;
import com.gaming.shop.models.entities.Customer;
import com.gaming.shop.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customersFound = customerRepository.findAll();
        List<CustomerDTO> customerFoundDTO = new ArrayList<>();
        customersFound.forEach(customer -> customerFoundDTO.add(objectMapper.convertValue(customer, CustomerDTO.class)));
        log.info("Customer retrieved: " + customerFoundDTO);
        return customerFoundDTO;
    }

    @Override
    public CustomerDTO updateCustomerById(long id, CustomerDTO customerDTO) {
        Customer customerFound = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer whit id " + id + " was not found!"));
        customerFound.setFirstName(customerDTO.getFirstName());
        customerFound.setLastName(customerDTO.getLastName());
        customerFound.setAge(customerDTO.getAge());
        customerFound.setEmail(customerDTO.getEmail());
        customerFound.setAddress(customerDTO.getAddress());
        customerFound.setPhoneNumber(customerDTO.getPhoneNumber());
        Customer customerSaved = customerRepository.save(customerFound);
        log.info("Customer whit id " + id + " was updated");
        return objectMapper.convertValue(customerSaved, CustomerDTO.class);
    }

    @Override
    public void deleteCustomerById(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            log.info("Customer whit id " + id + " has been deleted.");
        } else {
            throw new CustomerNotFoundException("This customer was not found.");
        }
    }
}
