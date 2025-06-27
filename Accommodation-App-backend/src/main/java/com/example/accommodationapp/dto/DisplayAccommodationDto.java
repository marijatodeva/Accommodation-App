package com.example.accommodationapp.dto;

import com.example.accommodationapp.model.domain.Accommodation;
import com.example.accommodationapp.model.domain.Host;
import com.example.accommodationapp.model.enumerations.Category;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        boolean isRented
//        List<DisplayReviewDto>reviewList,
//        double avgRating
) {

    public static DisplayAccommodationDto from(Accommodation accommodation){
//        double averageRating = 0.0;

//        if(accommodation.getReviewList() != null && !accommodation.getReviewList().isEmpty()){
//            averageRating = accommodation.getReviewList().stream()
//                    .filter(Objects::nonNull)
//                    .mapToDouble(review -> review.getRate() != null ? review.getRate() : 0)
//                    .average()
//                    .orElse(0.0);
//        }

        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.isRented()

//                accommodation.getReviewList().stream().map(DisplayReviewDto::from).collect(Collectors.toList()),
//                averageRating
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host hostId){
        return new Accommodation(name,category,hostId,numRooms);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Category category() {
        return category;
    }

    @Override
    public Long hostId() {
        return hostId;
    }

    @Override
    public Integer numRooms() {
        return numRooms;
    }

    @Override
    public boolean isRented() {
        return isRented;
    }

//    @Override
//    public List<DisplayReviewDto> reviewList() {
//        return reviewList;
//    }
//
//    @Override
//    public double avgRating() {
//        return avgRating;
//    }
}
