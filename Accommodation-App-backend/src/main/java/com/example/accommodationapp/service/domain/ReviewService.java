package com.example.accommodationapp.service.domain;

import com.example.accommodationapp.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();
    Optional<Review> save(Review review);
    void deleteById(Long id);
}
