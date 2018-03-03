package hello.app.services;

import hello.app.model.Order;
import hello.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order getOrderByOrderId(long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> getOrdersByUserName(String userName) {
        return orderRepository.findByUserName(userName);
    }

    public Order createOrder(Order order) {
        return orderRepository.insert(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
