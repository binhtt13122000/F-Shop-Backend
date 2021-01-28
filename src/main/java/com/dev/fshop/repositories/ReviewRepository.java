package com.dev.fshop.repositories;

import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    public Page<Review> findReviewsByProduct_ProductId(String productId, Pageable pageable);

    @Query("select u from Review u where u.product.productId = :productId and u.orders.account.userId = :userId and " +
            "u.status >= :status")
    public Page<Review> findReviewsByProductIdAndUserId(String productId, String userId, int status, Pageable pageable);

    @Query("select u from Review u where u.reviewId = :reviewId and u.orders.account.userId = :userId and u.status >= :status")
    public Review findReviewByReviewIdAndUserId(String reviewId, String userId, int status);
}
