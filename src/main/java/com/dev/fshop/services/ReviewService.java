package com.dev.fshop.services;

import com.dev.fshop.supporters.Review;
import java.util.List;

public interface ReviewService {
    //Get methods
    public Review findReviewByReviewId(String reviewId);

    public List<Review> findReviewByProductId(String proId);

    //Post methods
    public Review createNewReview(Review reviewEntity);

    //Put methods
    public Review updateReviewContentStar(String content, Integer star, String  reviewId);

    //Delete methods
    public boolean deleteReview(String reviewId);
}
