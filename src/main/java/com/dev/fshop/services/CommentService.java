package com.dev.fshop.services;


import com.dev.fshop.entities.CommentEntity;

import java.util.List;

public interface CommentService {
    //Get methods
    public List<CommentEntity> findCommentByProductId(String productId);

    //POST methods
    public CommentEntity createNewComment(CommentEntity comment);

    //Patch methods
    public CommentEntity updateCommentContent(CommentEntity comment, String commentId);

    //DELETE methods
    public boolean deleteComment(String commentId);
}
