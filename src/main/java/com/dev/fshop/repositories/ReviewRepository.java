package com.dev.fshop.repositories;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query("Select v.reviewId, v.content, v.proId, v.content, v.star  from OrderDetailEntity u inner join ReviewEntity v on u.orderId = v.orderId where u.name = :name")
    public List<CommentEntity> findCommentById(String name);

    @Query("DELETE from ReviewEntity u where u.reviewId = :reviewId ")
    public boolean deleteReview( Integer reviewId);
}
