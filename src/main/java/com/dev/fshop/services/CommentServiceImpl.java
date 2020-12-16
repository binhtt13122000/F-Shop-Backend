package com.dev.fshop.services;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentServiceInterface{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<CommentEntity> findCommentByUsername(String name) {
        return commentRepository.findCommentByName(name);
    }

    @Override
    public List<CommentEntity> findCommentByUserId(String userId) {
        return commentRepository.findCommentById(userId);
    }

    @Override
    public List<CommentEntity> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public CommentEntity findCommentByCommentId(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public CommentEntity createNewComment(CommentEntity commentEntity) {
        return commentRepository.insertCommentWithEntityManager(commentEntity);
    }

    @Override
    public CommentEntity updateCommentContent(String content, Integer commentId) {
        CommentEntity checkExisted = commentRepository.findById(commentId).orElse(null);
        if(checkExisted == null) {

        }
        return commentRepository.updateCommentContent(content, commentId);
    }

    @Override
    public boolean deleteComment(Integer commentId) {
        commentRepository.deleteComment(commentId);
        return true;
    }
}
