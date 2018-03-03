package hello.app.controllers;

import hello.app.model.Order;
import hello.app.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public Order getOrderByOrderId(@PathVariable Long orderId) {
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


}
