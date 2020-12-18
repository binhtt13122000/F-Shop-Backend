package com.dev.fshop.services;

import com.dev.fshop.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {
    //Get methods
    public ReviewEntity findReviewByReviewId(String reviewId);

    public List<ReviewEntity> findReviewByProductId(String proId);

    //Post methods
    public ReviewEntity createNewReview(ReviewEntity reviewEntity);

    //Put methods
    public ReviewEntity updateReviewContentStar(String content, Integer star, String  reviewId);

    //Delete methods
    public boolean deleteReview(String reviewId);
}
