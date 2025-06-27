package com.example.accommodationapp.dto;

import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.model.enumerations.Role;

public record CreateUserDto (
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role

){
    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}