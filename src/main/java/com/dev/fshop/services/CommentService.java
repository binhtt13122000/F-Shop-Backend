package com.dev.fshop.services;


import com.dev.fshop.entities.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentsByProductId(String productId);

    public Comment getCommentByCommentId(String commentId);

    public Comment createNewComment(Comment comment);

    public Comment updateComment(Comment comment);

    public boolean deleteComment(String commentId);

    public boolean confirmComment(Comment comment, int status);
}
