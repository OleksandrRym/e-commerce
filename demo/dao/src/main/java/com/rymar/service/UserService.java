package com.rymar.service;

import com.rymar.entity.User;
import com.rymar.repo.spring.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(UUID id) {
        userRepo.deleteById(id);
    }
}