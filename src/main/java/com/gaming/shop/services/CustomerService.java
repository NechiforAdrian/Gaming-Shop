package com.gaming.shop.services;

import com.gaming.shop.models.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomers();

    CustomerDTO updateCustomerById(long id, CustomerDTO customerDTO);

    void deleteCustomerById(long id);
}
