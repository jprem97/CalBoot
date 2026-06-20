package com.first.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.demo.model.User;
import com.first.demo.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User findUser (String mail){
           return repo.findByEmail(mail).orElse(new User("not found"));
    }
    public User creaUser (String email, String password){
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        repo.save(u);
        return u;
    }
    
}
