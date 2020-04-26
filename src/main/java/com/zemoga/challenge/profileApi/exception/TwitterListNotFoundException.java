package com.zemoga.challenge.profileApi.exception;

public class TwitterListNotFoundException extends RuntimeException {
    public TwitterListNotFoundException(String message, Exception e){
        super(message,e);
    }
}
