package com.first.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.exception.UserAlreadyFoundException;
import com.first.demo.models.LoginResponse;
import com.first.demo.models.UserRequest;
import com.first.demo.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) {
        try{
            userService.register(request);
            return new ResponseEntity<>("Successfully created user",HttpStatus.OK);
        }catch(UserAlreadyFoundException e){
            return new ResponseEntity<>("User already exists",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request) {
        try{
            LoginResponse response = userService.login(request);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    } 

}

