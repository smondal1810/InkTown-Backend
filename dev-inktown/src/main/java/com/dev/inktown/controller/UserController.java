package com.dev.inktown.controller;

import com.dev.inktown.entity.User;
import com.dev.inktown.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> getAllUser(){
        return new ArrayList<>();
    }
    @GetMapping(value = "/getById/{userId}")
    public User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User createdUser = userService.createUser(newUser);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/getOrdersForUser/{userId}")
    public Object getOrdersForUser(@PathVariable String userId){
        return userService.getOrdersForUser(userId);

    }
}
