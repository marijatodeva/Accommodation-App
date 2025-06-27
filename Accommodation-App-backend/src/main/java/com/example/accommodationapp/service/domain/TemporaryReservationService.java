package com.example.accommodationapp.service.domain;

import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<Accommodation> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservation> confirmReservation(String username);
}
