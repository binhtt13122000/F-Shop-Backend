package com.dev.fshop.services;

import com.dev.fshop.supporters.Comment;

import java.util.List;

public interface CommentService {
    //Get methods
    public List<Comment> findCommentByProductId(String productId);

    //POST methods
    public Comment createNewComment(Comment comment);

    //Patch methods
    public Comment updateCommentContent(Comment comment, String commentId);

    //DELETE methods
    public boolean deleteComment(String commentId);
}
