package com.retrospect.user.repository;

import com.retrospect.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

    public List<User> findByEmail(String email);

}
