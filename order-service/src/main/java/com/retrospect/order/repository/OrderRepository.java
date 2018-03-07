package com.retrospect.order.repository;


import com.retrospect.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Order findByOrderId(String orderId);

    public List<Order> findByUserName(String userName);

    void deleteByOrderId(String orderId);
}
