package com.dev.fshop.services;

import com.dev.fshop.entity.CommentEntity;

import java.util.List;

public interface CommentServiceInterface {
    //Get methods
    public List<CommentEntity> findCommentByUsername(String name);
    public List<CommentEntity> findCommentByUserId(String userId);
    public List<CommentEntity> getAllComments();
    public CommentEntity findCommentByCommentId(Integer commentId);

    //POST methods
    public CommentEntity createNewComment(CommentEntity commentEntity);

    //Put methods
    public CommentEntity updateCommentContent(String content, Integer commentId);

    //DELETE methods
    public boolean deleteComment(Integer commentId);
}
