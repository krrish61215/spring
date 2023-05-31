package com.nagarro.exittest_spring.repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nagarro.exittest_spring.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}

