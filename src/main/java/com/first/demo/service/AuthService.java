package com.first.demo.service;

import com.first.demo.dto.*;
import com.first.demo.entity.User;
import com.first.demo.exception.UserAlreadyFoundException;
import com.first.demo.exception.UserNotFoundException;
import com.first.demo.exception.IncorrectPasswordException;
import com.first.demo.repo.UserRepo;
import com.first.demo.security.jwtService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final jwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        Optional<User> newuser = userRepo.findByEmail(request.getEmail());
        if (newuser.isPresent()) {
            throw new UserAlreadyFoundException();
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAge(request.getAge());
        user.setWeight(request.getWeight());
        user.setHeight(request.getHeight());
        user.setGender(request.getGender());
        user.setGoalType(request.getGoalType());
        userRepo.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepo.save(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new IncorrectPasswordException("Invalid email or password");
        }

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepo.save(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String email = jwtService.extractEmail(request.getRefreshToken());

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!request.getRefreshToken().equals(user.getRefreshToken())) {
            throw new IncorrectPasswordException("Invalid Refresh Token");
        }

        String accessToken = jwtService.generateAccessToken(user);
        return new AuthResponse(accessToken, user.getRefreshToken());
    }

    public void logout(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setRefreshToken(null);
        userRepo.save(user);
    }
}
