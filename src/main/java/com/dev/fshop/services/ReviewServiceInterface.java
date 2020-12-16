package com.dev.fshop.services;

import com.dev.fshop.entity.ReviewEntity;

import java.util.List;

public interface ReviewServiceInterface {
    //Get methods
    public List<ReviewEntity> findReviewByUserName(String userName);
    public List<ReviewEntity> getAllReviews();
    public ReviewEntity findReviewByReviewId(Integer reviewId);


    //Post methods
    public ReviewEntity createNewReview(ReviewEntity reviewEntity);

    //Put methods
    public ReviewEntity updateReviewContentStar(String content, Integer star, Integer reviewId);

    //Delete methods
    public boolean deleteReview(Integer reviewId);
}
