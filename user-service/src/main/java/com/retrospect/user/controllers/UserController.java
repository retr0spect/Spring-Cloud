package com.retrospect.user.controllers;

import com.retrospect.user.model.User;
import com.retrospect.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public User getUserByUserName(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);
        return user;
    }

    @RequestMapping(value = "/{userName}/order/{orderId}", method = RequestMethod.GET)
    public User getUserByUserNameWithOrders(@PathVariable String userName, @PathVariable String orderId) {
        User user = userService.getUserByUserNameWithOrders(userName, orderId);
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


}
