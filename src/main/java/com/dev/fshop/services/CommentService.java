package com.dev.fshop.services;


import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CommentService {
    public Page<Comment> getCommentsByProductIdWithAdmin(String productId, Comment parent, Pageable pageable);

    public Page<Comment> getCommentsByProductIdWithUser(String userId, Comment parent, String productId, Pageable pageable);

    public Comment getCommentByCommentId(String commentId);

    public Comment getCommentByCommentIdAndUserId(String commentId, String userId);

    public Comment createNewComment(Comment comment, Comment parentId, Account account, Product product);

    public Comment updateComment(Comment currentComment, Comment newComment);

    public boolean changeStatusComment(Comment comment, int status);
}
