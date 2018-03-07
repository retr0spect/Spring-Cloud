package com.retrospect.user.repository;

import com.retrospect.user.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class OrderRedisRepositoryImpl implements OrderRedisRepository {
    private static final String HASH_NAME ="Order";

    private RedisTemplate<String, Order> redisTemplate;
    private HashOperations hashOperations;

    public OrderRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private OrderRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void saveOrder(Order order) {
        hashOperations.put(HASH_NAME, order.getOrderId(), order);
    }

    @Override
    public void deleteOrder(String OrderId) {
        hashOperations.delete(HASH_NAME, OrderId);
    }

    @Override
    public Order findOrder(String OrderId) {
        return (Order) hashOperations.get(HASH_NAME, OrderId);
    }



}
