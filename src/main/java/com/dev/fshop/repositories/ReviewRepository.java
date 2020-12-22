package com.dev.fshop.repositories;

import com.dev.fshop.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
//    @Query("update ReviewEntity  u set u.content = :password, u.star = :star  where  u.reviewId = :reviewId")
//    public ReviewEntity updateReviewContentStar(String content, Integer star, String reviewId);

//    public List<Review> findReviewEntitiesByProductEntity(String proId);

//    @Transactional
//    public ReviewEntity insertReviewWithEntityManager(ReviewEntity reviewEntity);

}
