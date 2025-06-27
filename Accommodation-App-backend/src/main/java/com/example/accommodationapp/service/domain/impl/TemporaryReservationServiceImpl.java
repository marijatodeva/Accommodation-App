package com.example.accommodationapp.service.domain.impl;

import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.TemporaryReservation;
import com.example.accommodationapp.model.domain.User;
import com.example.accommodationapp.model.exceptions.AccommodationNotAvailable;
import com.example.accommodationapp.model.exceptions.AccommodationNotFound;
import com.example.accommodationapp.model.exceptions.NoTemporaryReservations;
import com.example.accommodationapp.repository.TemporaryReservationRepository;
import com.example.accommodationapp.service.domain.AccommodationService;
import com.example.accommodationapp.service.domain.TemporaryReservationService;
import com.example.accommodationapp.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, UserService userService, AccommodationService accommodationService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return reservation.getAccommodations();
    }

    @Override
    public Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);
        if (accommodation.isRented()){
            throw new AccommodationNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(()->{
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setAccommodations(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getAccommodations().contains(accommodation)){
            reservation.getAccommodations().add(accommodation);
        }
        return Optional.of(temporaryReservationRepository.save(reservation));
    }

    @Override
    public Optional<TemporaryReservation> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Accommodation accommodation : reservation.getAccommodations()){
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }
        temporaryReservationRepository.delete(reservation);
        return Optional.of(reservation);
    }
}