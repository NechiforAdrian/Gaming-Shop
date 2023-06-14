package com.gaming.shop.services;

import com.gaming.shop.models.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(long id, OrderDTO orderDTO);

    List<OrderDTO> getOrders();

    void deleteOrderById(long id);
}
