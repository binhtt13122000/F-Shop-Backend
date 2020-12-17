package com.dev.fshop.repositories;

import com.dev.fshop.embedded.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {


    public boolean deleteReview(String reviewId);
    public Review updateReviewContentStar(String content, Integer star, String reviewId);
    public List<Review> findReviewByProductId(String proId);

    @Transactional
    public Review insertReviewWithEntityManager(Review review);
}
