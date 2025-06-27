package com.example.accommodationapp.service.domain.impl;

import com.example.accommodationapp.model.domain.Review;
import com.example.accommodationapp.repository.ReviewRepository;
import com.example.accommodationapp.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> save(Review review) {
        return Optional.of(reviewRepository.save(review));
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
