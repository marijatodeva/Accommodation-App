package com.example.accommodationapp.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Passwords do not match exception.");
    }
}
