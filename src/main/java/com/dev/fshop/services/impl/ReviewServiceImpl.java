package com.dev.fshop.services.impl;

import com.dev.fshop.embedded.Review;
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
        return reviewRepository.findReviewByProductId(proId);
    }


    @Override
    public Review createNewReview(Review reviewEntity) {
//        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.insertReviewWithEntityManager(reviewEntity);
    }

    @Override
    public Review updateReviewContentStar(String content, Integer star, String reviewId) {
        //        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.updateReviewContentStar(content,star,reviewId);
    }

    @Override
    public boolean deleteReview(String reviewId) {
        Review checkExisted = reviewRepository.findById(reviewId).orElse(null);
        if(checkExisted == null) {
            return false;
        }else {
            reviewRepository.deleteReview(reviewId);
            return true;
        }
    }


}
