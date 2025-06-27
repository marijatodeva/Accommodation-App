package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.DisplayAccommodationDto;
import com.example.accommodationapp.dto.TemporaryReservationDto;
import com.example.accommodationapp.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservationDto> confirmReservation(String username);
}
