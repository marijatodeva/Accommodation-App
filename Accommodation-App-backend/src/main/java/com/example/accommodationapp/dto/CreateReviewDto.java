package com.example.accommodationapp.dto;

import com.example.accommodationapp.model.domain.Review;

public record CreateReviewDto(
        String comment,
        Integer rate
) {

    public Review toReview(){
        return new Review(comment,rate);
    }

    @Override
    public String comment() {
        return comment;
    }

    @Override
    public Integer rate() {
        return rate;
    }
}
