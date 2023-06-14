package com.gaming.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.shop.exception.CustomerNotFoundException;
import com.gaming.shop.exception.OrderNotFoundException;
import com.gaming.shop.models.dtos.OrderDTO;
import com.gaming.shop.models.entities.Customer;
import com.gaming.shop.models.entities.Order;
import com.gaming.shop.repositories.CustomerRepository;
import com.gaming.shop.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final PriceService priceService;

    @Autowired
    public OrderServiceImpl(ObjectMapper objectMapper, CustomerRepository customerRepository, OrderRepository orderRepository, PriceService priceService) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.priceService = priceService;
    }

    @Transactional
    public OrderDTO createOrder(long id, OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " was not found!"));
        Order newOrder = objectMapper.convertValue(orderDTO, Order.class);
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setTotalPrice(priceService.setPriceProductsTier1());
        orderRepository.save(newOrder);
        return objectMapper.convertValue(newOrder, OrderDTO.class);
    }

    public List<OrderDTO> getOrders() {
        List<Order> orderFound = orderRepository.findAll();
        List<OrderDTO> orderFoundDTO = new ArrayList<>();
        orderFound.forEach(order -> orderFoundDTO.add(objectMapper.convertValue(order, OrderDTO.class)));
        log.info("Order retrieved: " + orderFoundDTO);
        return orderFoundDTO;
    }

    public void deleteOrderById(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            log.info("Order whit id " + id + " has been deleted.");
        } else {
            throw new OrderNotFoundException("This order was not found.");
        }
    }
}
