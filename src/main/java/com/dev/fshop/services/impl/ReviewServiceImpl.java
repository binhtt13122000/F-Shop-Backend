package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Review;
import com.dev.fshop.repositories.ReviewRepository;
import com.dev.fshop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewsByProductId(String productId) {
        return reviewRepository.findReviewsByProduct_ProductId(productId);
    }

    @Override
    public Review findReviewByReviewId(String reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Review postReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
        return true;
    }

    @Override
    public boolean confirmReview(Review review) {
        review.setStatus(1);
        return reviewRepository.save(review) != null?true:false;
    }
}
