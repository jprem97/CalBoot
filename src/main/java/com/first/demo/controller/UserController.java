package com.first.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.model.User;
import com.first.demo.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService service;
    // @GetMapping("/")
    // public String getMethodName() {
    //     return "Hi from 97";
    // }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
             User ans = service.findUser(user.getEmail()); 
             if(ans.getName()=="not found")  
                 ans = service.creaUser(user.getEmail(), user.getPassword());
             return ans;

    }
    
    

}