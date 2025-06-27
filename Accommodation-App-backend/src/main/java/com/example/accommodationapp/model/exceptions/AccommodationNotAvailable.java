package com.example.accommodationapp.model.exceptions;

public class AccommodationNotAvailable extends RuntimeException{
    public AccommodationNotAvailable() {
        super("Accommodation is not available");
    }
}
