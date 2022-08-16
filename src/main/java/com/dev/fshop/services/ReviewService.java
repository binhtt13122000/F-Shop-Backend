package com.dev.fshop.services;


import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public Page<Review> findReviewsByProductIdWithAdmin(String productId, Pageable pageable);

    public Page<Review> findReviewsByProductIdWithUserId(String productId, String userId, Pageable pageable);

    public Review findReviewByReviewIdWithAdmin(String reviewId);

    public Review findReviewByReviewIdWithUser(String reviewId, String userId);

    public Review postReview(Review review, Orders order, Product product);

    public Review updateReview(Review currentReview, Review newReview);

    public boolean changeStatusReview(Review review, int status);
}
