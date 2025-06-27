package com.example.accommodationapp.dto;

public record LoginResponseDto(
        String token
) {
    @Override
    public String token() {
        return token;
    }
}

