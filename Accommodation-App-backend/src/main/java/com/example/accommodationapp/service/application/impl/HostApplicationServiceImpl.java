package com.example.accommodationapp.service.application.impl;

import com.example.accommodationapp.dto.CreateHostDto;
import com.example.accommodationapp.dto.DisplayCountryDto;
import com.example.accommodationapp.dto.DisplayHostDto;
import com.example.accommodationapp.model.domain.Country;
import com.example.accommodationapp.model.domain.Host;
import com.example.accommodationapp.service.application.HostApplicationService;
import com.example.accommodationapp.service.domain.CountryService;
import com.example.accommodationapp.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());
        return hostService.update(id,
                hostDto.toHost(
                        country.orElse(null)
                )
        ).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());
        if (country.isPresent()) {
            return hostService.save(hostDto.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }
}
