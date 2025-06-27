package com.example.accommodationapp.service.application.impl;

import com.example.accommodationapp.dto.DisplayAccommodationDto;
import com.example.accommodationapp.dto.TemporaryReservationDto;
import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.TemporaryReservation;
import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.model.exceptions.AccommodationNotAvailable;
import com.example.accommodationapp.model.exceptions.AccommodationNotFound;
import com.example.accommodationapp.model.exceptions.NoTemporaryReservations;
import com.example.accommodationapp.repository.TemporaryReservationRepository;
import com.example.accommodationapp.service.application.TemporaryReservationApplicationService;
import com.example.accommodationapp.service.domain.AccommodationService;
import com.example.accommodationapp.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, AccommodationService accommodationService, UserService userService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return DisplayAccommodationDto.from(reservation.getAccommodations());
    }

    @Override
    public Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);
        if (accommodation.isRented()) {
            throw new AccommodationNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(() -> {
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setAccommodations(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getAccommodations().contains(accommodation)) {
            reservation.getAccommodations().add(accommodation);
        }

        TemporaryReservation saved = temporaryReservationRepository.save(reservation);
        return Optional.of(TemporaryReservationDto.from(saved));
    }

    @Override
    public Optional<TemporaryReservationDto> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Accommodation accommodation : reservation.getAccommodations()) {
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }
        TemporaryReservationDto dto = TemporaryReservationDto.from(reservation);
        temporaryReservationRepository.delete(reservation);
        return Optional.of(dto);
    }
}
