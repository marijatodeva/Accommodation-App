package com.example.accommodationapp.service.application.impl;


import com.example.accommodationapp.dto.CreateUserDto;
import com.example.accommodationapp.dto.DisplayUserDto;
import com.example.accommodationapp.dto.LoginResponseDto;
import com.example.accommodationapp.dto.LoginUserDto;
import com.example.accommodationapp.helpers.JwtHelper;
import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.repository.UserRepository;
import com.example.accommodationapp.service.application.UserApplicationService;
import com.example.accommodationapp.service.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        return userRepository.findByUsername(loginUserDto.username())
                .filter(user -> passwordEncoder.matches(loginUserDto.password(), user.getPassword()))
                .map(user -> new LoginResponseDto(jwtHelper.generateToken(user)));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}

