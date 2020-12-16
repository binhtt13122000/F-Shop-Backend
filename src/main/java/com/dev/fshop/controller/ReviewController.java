package com.dev.fshop.controller;

import com.dev.fshop.entity.ReviewEntity;
import com.dev.fshop.services.ReviewServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class ReviewController {
    @Autowired
    private ReviewServiceInterface reviewServiceInterface;

    @GetMapping(path = "/reviews")
    public ResponseEntity<List<ReviewEntity>> getAllReviews() {
        return ResponseEntity.ok().body(reviewServiceInterface.getAllReviews());
    }

    @GetMapping(path = "/reviews/{reviewId}")
    public ResponseEntity<ReviewEntity> findReviewByReviewId(@PathVariable Integer reviewId) {
        return  ResponseEntity.ok().body(reviewServiceInterface.findReviewByReviewId(reviewId));
    }

    @GetMapping(path = "/reviews/{userName}")
    public ResponseEntity<List<ReviewEntity>> findReviewByUserName(@PathVariable String userName) {
        return ResponseEntity.ok().body(reviewServiceInterface.findReviewByUserName(userName));
    }

    @PostMapping(path = "/reviews")
    public ResponseEntity<ReviewEntity> createNewReview(@RequestBody ReviewEntity reviewEntity) {
        return ResponseEntity.ok().body(reviewServiceInterface.createNewReview(reviewEntity));
    }

    @PutMapping(path = "/reviews")
    public ResponseEntity<ReviewEntity> updateReviewContentStar(@RequestParam(name = "content")String content, @RequestParam(name = "star")Integer star,
                                                @RequestParam(name = "reviewId")Integer reviewId) {
        return ResponseEntity.ok().body(reviewServiceInterface.updateReviewContentStar(content,star,reviewId));
    }

    @DeleteMapping(path = "/reviews/{reviewId}")
    public boolean deleteReviewByReviewId(@PathVariable Integer reviewId) {
        return  reviewServiceInterface.deleteReview(reviewId);
    }
}
