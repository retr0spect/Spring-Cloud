package hello.app.controllers;

import hello.app.model.User;
import hello.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{userName}/orders", method = RequestMethod.GET)
    public User getUserByUserNameWithOrders(@PathVariable String userName) {
        User user = userService.getUserByUserNameWithOrders(userName);
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


}
