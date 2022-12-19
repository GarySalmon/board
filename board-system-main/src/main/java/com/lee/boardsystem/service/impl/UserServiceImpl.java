package com.lee.boardsystem.service.impl;

import com.lee.boardsystem.repository.UserRepo;
import com.lee.boardsystem.model.User;
import com.lee.boardsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String addUser(User user){
        userRepo.saveAndFlush(user);
        return "ServiceImpl save user";
    }

    @Override
    public String updateUser(Integer userId,User user) {
        User u = userRepo.findById(userId).orElse(null);
        if(u!=null){
            u.setName(user.getName());
            u.setAccount(user.getAccount());
            u.setPassword(user.getPassword());
            userRepo.saveAndFlush(u);
            return "ServiceImpl update user";
        }else{
            return "no data update error";
        }
    }

    @Override
    public String deleteUser(Integer userId) {
        userRepo.deleteById(userId);
        return "ServiceImpl delete user";
    }

    @Override
    public User getUser(Integer userId) {
        User user = userRepo.findById(userId).orElse(null);
        return user;
    }


}
