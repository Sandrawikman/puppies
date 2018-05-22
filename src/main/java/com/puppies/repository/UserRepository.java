package com.puppies.repository;

public interface UserRepository {

    int createUser(String username, String password, String email);

    void updateUser(int userId, String username, String password, String email);

    void deleteUser(int userId);

    Integer checkLogin(String username, String password);

    boolean usernameAlreadyTaken(String username);

}
