package com.example.accommodationapp.service.application;

import com.example.accommodationapp.dto.CreateAccommodationDto;
import com.example.accommodationapp.dto.CreateReviewDto;
import com.example.accommodationapp.dto.DisplayAccommodationDto;
import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.Country;
import com.example.accommodationapp.model.domain.Review;
import com.example.accommodationapp.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> markAsRented(Long id);

    void deleteById(Long id);

//    Optional<DisplayAccommodationDto> addReview(Long id, CreateReviewDto reviewDto);

    List<DisplayAccommodationDto> search(String name, Category category, Long hostId, Integer numOfRooms);

    Page<DisplayAccommodationDto> findAll(Pageable pageable);


}
