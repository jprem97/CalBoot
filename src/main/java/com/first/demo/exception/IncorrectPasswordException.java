package com.first.demo.exception;

public class IncorrectPasswordException extends RuntimeException {
   public  IncorrectPasswordException(String msg){
        super(msg);
    }
}
