package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.CreateUserDto;
import com.example.accommodationapp.dto.DisplayUserDto;
import com.example.accommodationapp.dto.LoginResponseDto;
import com.example.accommodationapp.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
