package com.example.accommodationapp.dto;

import com.example.accommodationapp.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toCountry(){
        return new Country(name, continent);
    }
}
