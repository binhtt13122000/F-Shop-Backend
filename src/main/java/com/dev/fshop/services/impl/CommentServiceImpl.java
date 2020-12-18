package com.dev.fshop.services.impl;

import com.dev.fshop.entities.CommentEntity;
import com.dev.fshop.repositories.CommentRepository;
import com.dev.fshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Override
//    public List<CommentEntity> findCommentByProductId(String productId) {
////        return commentRepository.findCommentByProduct(productId);
//        return null;
//    }
//
//    @Override
//    public CommentEntity createNewComment(CommentEntity comment) {
////        return commentRepository.insertCommentWithEntityManager(comment);
//        return null;
//    }
//
//    @Override
//    public CommentEntity updateCommentContent(CommentEntity comment, String commentId) {
//        CommentEntity checkExisted = commentRepository.findById(commentId).orElse(null);
//        if (checkExisted == null) {
//
//        }
////        return commentRepository.updateCommentContent(comment, commentId);
//        return null;
//    }
//
//    @Override
//    public boolean deleteComment(String commentId) {
////        commentRepository.deleteComment(commentId);
//        return true;
//    }
}
