package com.dev.fshop.controller;

import com.dev.fshop.entity.ReviewEntity;
import com.dev.fshop.services.ReviewServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class ReviewController {
    @Autowired
    private ReviewServiceInterface reviewServiceInterface;

    @GetMapping(path = "/reviews")
    public List<ReviewEntity> getAllReviews() {
        return reviewServiceInterface.getAllReviews();
    }

    @GetMapping(path = "/reviews/{reviewId}")
    public ReviewEntity findReviewByReviewId(@PathVariable Integer reviewId) {
        return  reviewServiceInterface.findReviewByReviewId(reviewId);
    }

    @GetMapping(path = "/reviews/{userName}")
    public List<ReviewEntity> findReviewByUserName(@PathVariable String userName) {
        return reviewServiceInterface.findReviewByUserName(userName);
    }

    @PostMapping(path = "/reviews")
    public ReviewEntity createNewReview(@RequestBody ReviewEntity reviewEntity) {
        return reviewServiceInterface.createNewReview(reviewEntity);
    }

    @PutMapping(path = "/reviews")
    public ReviewEntity updateReviewContentStar(@RequestParam(name = "content")String content, @RequestParam(name = "star")Integer star,
                                                @RequestParam(name = "reviewId")Integer reviewId) {
        return reviewServiceInterface.updateReviewContentStar(content,star,reviewId);
    }

    @DeleteMapping(path = "/reviews/{reviewId}")
    public boolean deleteReviewByReviewId(@PathVariable Integer reviewId) {
        return  reviewServiceInterface.deleteReview(reviewId);
    }
}
