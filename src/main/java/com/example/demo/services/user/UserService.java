package com.example.demo.services.user;

import com.example.demo.model.User;
import com.example.demo.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository UserRepository;

    @Override
    public Iterable<User> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return UserRepository.findById(id);
    }

    @Override
    public void save(User user) {
        UserRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        UserRepository.delete(user);
    }
}

