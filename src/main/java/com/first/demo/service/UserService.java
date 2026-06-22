package com.first.demo.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.first.demo.entity.User;
import com.first.demo.exception.UserAlreadyFoundException;
import com.first.demo.exception.UserNotFoundException;
import com.first.demo.models.LoginResponse;
import com.first.demo.models.UserRequest;
import com.first.demo.exception.IncorrectPasswordException;
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

     public LoginResponse login (UserRequest request){
        Optional<User> user = userRepo.findByEmail(request.getEmail());
        if(!user.isPresent()) 
            throw new UserNotFoundException("Not found the user  ...register bro");
        else if(!request.getPassword().equals(user.get().getPassword())) 
            throw new IncorrectPasswordException("worng password");
        return new LoginResponse(request.getEmail(),"refresh-token"); 
    }
}