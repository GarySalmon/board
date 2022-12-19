package com.lee.boardsystem.service;

import com.lee.boardsystem.model.User;

public interface UserService {
    public String addUser(User user);

    public String updateUser(Integer userId,User user);

    public String deleteUser(Integer userId);

    public User getUser(Integer userId);
}
