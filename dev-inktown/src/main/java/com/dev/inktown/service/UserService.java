package com.dev.inktown.service;


import com.dev.inktown.entity.User;
import com.dev.inktown.model.Role;
import com.dev.inktown.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderService orderService;

    public User getUserById(String userId){
        Optional<User> result = userRepository.findById(userId);
        return result.orElseGet(User::new);
    }
    public User createUser(User newUser){
        return userRepository.save(newUser);
    }
    public Object getOrdersForUser(String userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Role role = optionalUser.get().getRole();
            if(role== Role.ROLE_CUST){
                return getOrdersForCustomer(userId);
            }else{
                return getOrdersForEmployee(userId);
            }
        }
        return optionalUser;
    }
    public Object getOrdersForEmployee(String userId){
        return orderService.getOrderListForEmployee(userId);
    }

    public Object getOrdersForCustomer(String userId){
        return orderService.getOrderListForCust(userId);
    }

    public User getUserByPhoneNo(String userPhone) {

        log.info("123");
        User res = userRepository.findByUserPhoneNo(userPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        log.info("123"+res);
        return res;

    }
}
