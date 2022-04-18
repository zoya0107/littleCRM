package com.pet.littlecrm.service;

import com.pet.littlecrm.model.Order;
import com.pet.littlecrm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        this.orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        Order order = null;
        if (optional.isPresent()) {
            order = optional.get();
        }
        else {
            throw new RuntimeException("There is no order with id " + id);
        }
        return order;
    }
}
