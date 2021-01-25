package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Review;
import com.dev.fshop.repositories.ReviewRepository;
import com.dev.fshop.services.ReviewService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Review> findReviewsByProductIdWithAdmin(String productId, Pageable pageable) {
        return reviewRepository.findReviewsByProduct_ProductId(productId, pageable);
    }

    @Override
    public Page<Review> findReviewsByProductIdWithUserId(String productId, String userId, Pageable pageable) {
        return reviewRepository.findReviewsByProductIdAndUserId(productId, userId, 0, pageable);
    }

    @Override
    public Review findReviewByReviewIdWithAdmin(String reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Review findReviewByReviewIdWithUser(String reviewId, String userId) {
        return reviewRepository.findReviewByReviewIdAndUserId(reviewId, userId, 0);
    }

    @Override
    public Review postReview(Review review, Orders order, Product product) {
        review.setOrderId(order.getOrderId());
        review.setOrders(order);
        review.setProduct(product);
        review.setProductId(product.getProductId());
        review.setCreateTime(new Date());
        review.setStatus(0);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review currentReview, Review newReview) {
        newReview.setReviewId(currentReview.getReviewId());
        newReview.setOrders(currentReview.getOrders());
        newReview.setOrderId(currentReview.getOrderId());
        newReview.setProductId(currentReview.getProductId());
        newReview.setProduct(currentReview.getProduct());
        return reviewRepository.save(newReview);
    }

    @Override
    public boolean changeStatusReview(Review review, int status) {
        review.setStatus(status);
        reviewRepository.save(review);
        return true;
    }


}
