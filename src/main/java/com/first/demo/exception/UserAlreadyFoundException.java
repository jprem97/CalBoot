package com.first.demo.exception;

public class UserAlreadyFoundException extends RuntimeException {
    public UserAlreadyFoundException() {
        super("User already exists with this email");
    }
}
