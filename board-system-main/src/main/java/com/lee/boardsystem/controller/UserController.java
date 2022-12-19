package com.lee.boardsystem.controller;

import com.lee.boardsystem.model.User;
import com.lee.boardsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @CrossOrigin(origins = "http://localhost:8081")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public String updateUser(@PathVariable Integer userId,
                          @RequestBody User user){
        return userService.updateUser(userId,user);
    }

    @DeleteMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public String deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public User getUser(@PathVariable Integer userId){
        User user = userService.getUser(userId);
        return user;
    }

}
