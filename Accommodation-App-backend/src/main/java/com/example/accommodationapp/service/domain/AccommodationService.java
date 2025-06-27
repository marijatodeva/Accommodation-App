package com.example.accommodationapp.service.domain;

import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> markAsRented(Long id);
    void deleteById(Long id);
//    Optional<Accommodation>addReview(Long id, Review review);
    void refreshMaterializedView();
    Page<Accommodation> findAll(Pageable pageable);
}
