package com.dev.fshop.services;


import com.dev.fshop.entities.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> findReviewsByProductId(String productId);

    public Review findReviewByReviewId(String reviewId);

    public Review postReview(Review review);

    public Review updateReview(Review review);

    public boolean deleteReview(String reviewId);

    public boolean confirmReview(Review review);
}
