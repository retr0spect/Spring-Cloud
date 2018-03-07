package com.retrospect.user.services;

import com.retrospect.user.clients.OrderRestTemplateClient;
import com.retrospect.user.model.Order;
import com.retrospect.user.model.User;
import com.retrospect.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRestTemplateClient orderRestTemplateClient;

    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUserNameWithOrders(String userName, String orderId) {
        User user = userRepository.findByUserName(userName);
        //List<Order> orders = orderRestTemplateClient.getOrders(user.getUserName());
        //user.setOrders(orders);
        Order order = orderRestTemplateClient.getOrder(orderId);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        user.setOrders(orders);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
