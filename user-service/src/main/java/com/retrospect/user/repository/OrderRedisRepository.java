package com.retrospect.user.repository;

import com.retrospect.user.model.Order;

public interface OrderRedisRepository {
    void saveOrder(Order org);
    void deleteOrder(String orderId);
    Order findOrder(String orderId);
}
