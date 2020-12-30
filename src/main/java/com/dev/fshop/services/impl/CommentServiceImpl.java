package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Comment;
import com.dev.fshop.repositories.CommentRepository;
import com.dev.fshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByProductId(String productId) {
//        return commentRepository.findCommentsByProductProId(productId);
        return null;
    }

    @Override
    public Comment getCommentByCommentId(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment createNewComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    public boolean confirmComment(Comment comment, int status) {
        comment.setStatus(1);
        commentRepository.save(comment);
        return true;
    }
}
