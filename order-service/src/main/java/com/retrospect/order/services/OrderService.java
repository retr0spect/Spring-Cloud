package com.retrospect.order.services;

import com.retrospect.order.events.source.SimpleSourceBean;
import com.retrospect.order.model.Order;
import com.retrospect.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    public Order getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> getOrdersByUserName(String userName) {
        return orderRepository.findByUserName(userName);
    }

    public Order createOrder(Order order) {
        order.setOrderId( UUID.randomUUID().toString());
        simpleSourceBean.publishOrgChange("SAVE", order.getOrderId());
        return orderRepository.insert(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(String orderId) {
        simpleSourceBean.publishOrgChange("DELETE", orderId);
        orderRepository.deleteByOrderId(orderId);
    }
}
