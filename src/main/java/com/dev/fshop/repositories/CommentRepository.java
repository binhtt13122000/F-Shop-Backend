package com.dev.fshop.repositories;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("Select v.commentId, v.content, v.proId   from CommentEntity v where v.name = :name ")
    public List<CommentEntity> findCommentByName(String name);

    @Query("Select v.commentId, v.content, v.proId  from CustomerEntity u inner join CommentEntity v on u.userId = v.userId ")
    public List<CommentEntity> findCommentById(String userId);

    @Query("DELETE from CommentEntity u where u.commentId = :commentId ")
    public boolean deleteComment( Integer commentId);
}
