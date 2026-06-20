package com.first.demo.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.first.demo.dto.UserRequest;
import com.first.demo.exception.UserAlreadyFoundException;
import com.first.demo.model.User;
import com.first.demo.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService{

    private UserRepo userRepo;

    public void register (UserRequest request){
        Optional<User> user = userRepo.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyFoundException();
        }
        User newUser = new User(request.getEmail(),request.getPassword());
        userRepo.save(newUser);
    }
}