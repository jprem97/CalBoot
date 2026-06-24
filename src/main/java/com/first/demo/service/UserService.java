// package com.first.demo.service;

// import java.util.Optional;


// import org.springframework.stereotype.Service;

// import com.first.demo.dto.LoginResponse;
// import com.first.demo.dto.RegisterRequest;
// import com.first.demo.entity.User;
// import com.first.demo.exception.UserAlreadyFoundException;
// import com.first.demo.exception.UserNotFoundException;
// import com.first.demo.exception.IncorrectPasswordException;
// import com.first.demo.repo.UserRepo;

// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class UserService{

//     private UserRepo userRepo;

//     // public void register (RegisterRequest request){
//     //     Optional<User> user = userRepo.findByEmail(request.getEmail());
//     //     if(user.isPresent()){
//     //         throw new UserAlreadyFoundException();
//     //     }
//     //     User newUser = new User(request.getEmail(),request.getPassword());
//     //     userRepo.save(newUser);
//     // }

//     //  public LoginResponse login (RegisterRequest request){
//     //     Optional<User> user = userRepo.findByEmail(request.getEmail());
//     //     if(!user.isPresent()) 
//     //         throw new UserNotFoundException("Not found the user  ...register bro");
//     //     else if(!request.getPassword().equals(user.get().getPassword())) 
//     //         throw new IncorrectPasswordException("worng password");
//     //     return new LoginResponse(request.getEmail(),"refresh-token"); 
//     // }
// }