package com.dev.fshop.controllers;

import com.dev.fshop.entities.ReviewEntity;
import com.dev.fshop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @GetMapping(path = "/products/{productId}/reviews")
    public ResponseEntity<List<ReviewEntity>> findReviewByProductId(@PathVariable String productId) {
        return  ResponseEntity.ok().body(reviewService.findReviewByProductId(productId));
    }

    @PostMapping(path = "/reviews")
    public ResponseEntity<ReviewEntity> createNewReview(@RequestBody ReviewEntity review) {
        return ResponseEntity.ok().body(reviewService.createNewReview(review));
    }

    @PutMapping(path = "/reviews")
    public ResponseEntity<ReviewEntity> updateReviewContentStar(
            @RequestParam(name = "content")String content,
            @RequestParam(name = "star")Integer star,
            @RequestParam(name = "reviewId")String reviewId) {
        return ResponseEntity.ok().body(reviewService.updateReviewContentStar(content,star,reviewId));
    }

    @DeleteMapping(path = "/reviews/{reviewId}")
    public boolean deleteReviewByReviewId(@PathVariable String reviewId) {
        return  reviewService.deleteReview(reviewId);
    }
}
