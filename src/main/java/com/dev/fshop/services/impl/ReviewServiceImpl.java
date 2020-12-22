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
    public Review findReviewByReviewId(String  reviewId) {
        return  reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<Review> findReviewByProductId(String proId) {
//        return reviewRepository.findReviewEntitiesByProductEntity(proId);
        return null;

    }


    @Override
    public Review createNewReview(Review review) {
//        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReviewContentStar(String content, Integer star, String reviewId) {
        //        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return null;
    }

    @Override
    public boolean deleteReview(String reviewId) {
        Review checkExisted = reviewRepository.findById(reviewId).orElse(null);
        if(checkExisted == null) {
            return false;
        }else {
            reviewRepository.deleteById(reviewId);
            return true;
        }
    }


}
