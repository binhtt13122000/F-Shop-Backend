package com.dev.fshop.repositories;

import com.dev.fshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {
    public boolean deleteReview( String reviewId);
    public ReviewEntity updateReviewContentStar(String content, Integer star, String reviewId);
    public List<ReviewEntity> findReviewByProductId(String proId);

    @Transactional
    public ReviewEntity insertReviewWithEntityManager(ReviewEntity reviewEntity);

}
