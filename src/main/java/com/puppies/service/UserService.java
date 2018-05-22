package com.puppies.service;

public interface UserService {

    int createUser(String username, String password, String Email);

    void updateUser(int userId, String username, String password, String Email);

    void deleteUser(int userId);

    boolean usernameAlreadyTaken(String username);

    Integer checkLogin(String username, String password);

}
