package com.example.accommodationapp.repository;

import com.example.accommodationapp.model.domain.TemporaryReservation;
import com.example.accommodationapp.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    Optional<TemporaryReservation> findByUser(User user);
}
