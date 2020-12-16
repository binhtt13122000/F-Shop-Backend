package com.dev.fshop.repositories;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query("Select v.reviewId, v.content, v.proId, v.content, v.star  from OrderDetailEntity u inner join ReviewEntity v on u.orderId = v.orderId where u.name = :name")
    public List<ReviewEntity> findReviewByName(String name);

    @Query("Select v.reviewId, v.content, v.proId, u.name  from OrderDetailEntity u inner join ReviewEntity v on u.orderId = v.orderId where v.reviewId = :reviewId")
    public ReviewEntity findReviewById(Integer reviewId);

    @Query("DELETE from ReviewEntity u where u.reviewId = :reviewId ")
    public boolean deleteReview( Integer reviewId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ReviewEntity u set u.content = :content,u.star = :star where u.reviewId = :reviewId ")
    public ReviewEntity updateReviewContentStar(String content, Integer star, Integer reviewId);

    @Transactional
    public ReviewEntity insertReviewWithEntityManager(ReviewEntity reviewEntity);
}
