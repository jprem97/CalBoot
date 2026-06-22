package com.first.demo.exception;

public class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(String m){
        super(m);
    }
}

