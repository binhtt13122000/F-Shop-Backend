package com.dev.fshop.services;

import com.dev.fshop.entity.ReviewEntity;
import com.dev.fshop.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewServiceInterface{
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<ReviewEntity> findReviewByUserName(String userName) {
        return reviewRepository.findReviewByName(userName);
    }

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public ReviewEntity findReviewByReviewId(Integer reviewId) {
        return  reviewRepository.findReviewById(reviewId);
    }

    @Override
    public ReviewEntity createNewReview(ReviewEntity reviewEntity) {
//        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.insertReviewWithEntityManager(reviewEntity);
    }

    @Override
    public ReviewEntity updateReviewContentStar(String content, Integer star, Integer reviewId) {
        //        ReviewEntity checkExisted = reviewRepository.findById(reviewEntity.getReviewId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return reviewRepository.updateReviewContentStar(content,star,reviewId);
    }

    @Override
    public boolean deleteReview(Integer reviewId) {
                ReviewEntity checkExisted = reviewRepository.findById(reviewId).orElse(null);
        if(checkExisted == null) {
            return false;
        }else {
            reviewRepository.deleteReview(reviewId);
            return true;
        }

    }
}
