package com.example.accommodationapp.service.application.impl;

import com.example.accommodationapp.dto.CreateAccommodationDto;
import com.example.accommodationapp.dto.CreateReviewDto;
import com.example.accommodationapp.dto.DisplayAccommodationDto;
import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.Host;
import com.example.accommodationapp.model.domain.Review;
import com.example.accommodationapp.model.enumerations.Category;
import com.example.accommodationapp.repository.AccommodationRepository;
import com.example.accommodationapp.service.application.AccommodationApplicationService;
import com.example.accommodationapp.service.domain.AccommodationService;
import com.example.accommodationapp.service.domain.HostService;
import com.example.accommodationapp.service.domain.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.accommodationapp.model.exceptions.HostNotFound;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final ReviewService reviewService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, ReviewService reviewService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.reviewService = reviewService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());
        return accommodationService.update(id,
                accommodation.toAccommodation(
                        host.orElse(null)
                )
        ).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());

        if (host.isPresent()) {
            return accommodationService.save(accommodation.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accommodationService.save(accommodation)
                    .map(DisplayAccommodationDto::from)
                    .orElse(null);
        });
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

//    @Override
//    public Optional<DisplayAccommodationDto> addReview(Long id, CreateReviewDto reviewDto) {
//        return accommodationService.findById(id).map(accommodation -> {
//            Review review = reviewDto.toReview();
//
//            review = reviewService.save(review).orElseThrow(() -> new RuntimeException("Review not saved"));
//
//            accommodation.getReviewList().add(review);
//
//            return accommodationService.update(id, accommodation)
//                    .map(DisplayAccommodationDto::from)
//                    .orElse(null);
//        });
//    }

    @Override
    public List<DisplayAccommodationDto> search(String name, Category category, Long hostId, Integer numRooms) {
        Host host = (hostId != null) ? hostService.findById(hostId).orElseThrow(HostNotFound::new) : null;

        return accommodationService.findAll().stream()
                .filter(a -> name == null || a.getName().contains(name))
                .filter(a -> category == null || a.getCategory().equals(category))
                .filter(a -> host == null || a.getHost().equals(host))
                .filter(a -> numRooms == null || a.getNumRooms().equals(numRooms))
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DisplayAccommodationDto> findAll(Pageable pageable) {
        return accommodationService.findAll(pageable)
                .map(DisplayAccommodationDto::from);
    }


}