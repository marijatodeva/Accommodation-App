package com.example.accommodationapp.model.exceptions;

public class HostNotFound extends RuntimeException{
    public HostNotFound() {
        super("Host not found");
    }
}
