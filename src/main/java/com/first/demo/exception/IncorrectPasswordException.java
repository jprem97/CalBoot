package com.first.demo.exception;

public class IncorrectPasswordException extends RuntimeException {
   public  IncorrectPasswordException(String m){
        super(m);
    }
}
