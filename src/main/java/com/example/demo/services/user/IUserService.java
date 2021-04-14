package com.example.demo.services.user;

import com.example.demo.model.Product;
import com.example.demo.model.User;

import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void deleteUser(User user);

}
