package com.puppies.service;

import com.puppies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int createUser(String username, String password, String email) {
        return userRepository.createUser(username, password, email);
    }

    @Override
    public void updateUser(int userId, String username, String password, String email) {
        userRepository.updateUser(userId, username, password, email);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public boolean usernameAlreadyTaken(String username) {
        return userRepository.usernameAlreadyTaken(username);
    }

    @Override
    public Integer checkLogin(String username, String password) {
        return userRepository.checkLogin(username, password);
    }
}
