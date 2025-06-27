package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.CreateReviewDto;
import com.example.accommodationapp.dto.DisplayReviewDto;
import com.example.accommodationapp.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {
    List<DisplayReviewDto> findAll();

    Optional<DisplayReviewDto> findById(Long id);

    Optional<DisplayReviewDto> update(Long id, CreateReviewDto reviewDto);

    Optional<DisplayReviewDto> save(CreateReviewDto reviewDto);

    void deleteById(Long id);
}
