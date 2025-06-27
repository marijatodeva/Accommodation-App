package com.example.accommodationapp.model.exceptions;

public class AccommodationNotFound extends RuntimeException{
    public AccommodationNotFound() {
        super("Accommodation not found");
    }
}
