package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.CreateCountryDto;
import com.example.accommodationapp.dto.DisplayCountryDto;
import com.example.accommodationapp.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    void deleteById(Long id);
}
