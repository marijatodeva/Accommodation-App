package com.example.accommodationapp.model.exceptions;

public class NoTemporaryReservations extends RuntimeException{
    public NoTemporaryReservations() {
        super("No temporary reservations");
    }
}

