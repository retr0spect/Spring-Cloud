package com.retrospect.order.controllers;

import com.retrospect.order.model.Order;
import com.retrospect.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Order getOrderByOrderId(@PathVariable String orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    public List<Order> getOrdersByUserName(@PathVariable String userName) {
        return orderService.getOrdersByUserName(userName);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public void createOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }


}
