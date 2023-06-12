package com.gaming.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.shop.exception.OrderNotFoundException;
import com.gaming.shop.models.dtos.OrderDTO;
import com.gaming.shop.models.entities.Order;
import com.gaming.shop.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final ObjectMapper objectMapper;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ObjectMapper objectMapper, OrderRepository orderRepository) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = objectMapper.convertValue(orderDTO, Order.class);
        Order savedOrder = orderRepository.save(order);
        log.info("Order " + savedOrder.getFirstName() + " was created!");
        return objectMapper.convertValue(savedOrder, OrderDTO.class);
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
