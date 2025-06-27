package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.CreateHostDto;
import com.example.accommodationapp.dto.DisplayHostDto;
import com.example.accommodationapp.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    void deleteById(Long id);
}
