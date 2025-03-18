package com.banking.api.services;

import com.banking.api.entity.User;
import com.banking.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ROLE_USER")); //
        return userRepository.save(user);
    }



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User updateUser(User user) {
        return userRepository.save(user);
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
