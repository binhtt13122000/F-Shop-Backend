package com.dev.fshop.services.impl;

import com.dev.fshop.supporters.Comment;
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
    public List<Comment> findCommentByProductId(String productId){
        return commentRepository.findCommentByProduct(productId);
    }

    @Override
    public Comment createNewComment(Comment comment) {
        return commentRepository.insertCommentWithEntityManager(comment);
    }

    @Override
    public Comment updateCommentContent(Comment comment, String commentId) {
        Comment checkExisted = commentRepository.findById(commentId).orElse(null);
        if(checkExisted == null) {

        }
        return commentRepository.updateCommentContent(comment, commentId);
    }

    @Override
    public boolean deleteComment(String commentId) {
        commentRepository.deleteComment(commentId);
        return true;
    }
}
