package com.example.accommodationapp.model.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("User not found");
    }
}
