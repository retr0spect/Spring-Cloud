package hello.app.services;

import hello.app.clients.OrdersFeignClient;
import hello.app.model.Order;
import hello.app.model.User;
import hello.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrdersFeignClient ordersFeignClient;

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUserNameWithOrders(String userName) {
        User user = userRepository.findByUserName(userName);
        List<Order> orders = ordersFeignClient.getOrders(user.getUserName());
        user.setOrders(orders);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
