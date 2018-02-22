package hello.app.services;

import hello.app.model.User;
import hello.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
