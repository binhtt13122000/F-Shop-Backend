package com.dev.fshop.repositories;

import com.dev.fshop.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

//    public List<CommentEntity> findCommentByName(String name);
//    public boolean deleteComment(String commentId);
//    public CommentEntity updateCommentContent(CommentEntity commentEntity, String commentId);
    public List<CommentEntity> findCommentByProduct(String proId);
//    @Transactional
//    public CommentEntity insertCommentWithEntityManager(CommentEntity commentEntity);
}
