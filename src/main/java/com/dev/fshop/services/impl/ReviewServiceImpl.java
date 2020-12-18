package com.dev.fshop.services.impl;

import com.dev.fshop.entities.ReviewEntity;
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
    public ReviewEntity findReviewByReviewId(String  reviewId) {
        return  reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<ReviewEntity> findReviewByProductId(String proId) {
        return reviewRepository.findReviewEntitiesByProductEntity(proId);
    }


    @Override
    public ReviewEntity createNewReview(ReviewEntity reviewEntity) {
//        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.save(reviewEntity);
    }

    @Override
    public ReviewEntity updateReviewContentStar(String content, Integer star, String reviewId) {
        //        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return null;
    }

    @Override
    public boolean deleteReview(String reviewId) {
        ReviewEntity checkExisted = reviewRepository.findById(reviewId).orElse(null);
        if(checkExisted == null) {
            return false;
        }else {
            reviewRepository.deleteById(reviewId);
            return true;
        }
    }


}
