package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.CommentRepository;
import com.dev.fshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getCommentsByProductIdWithAdmin(String productId, Comment parent, Pageable pageable) {
        if (parent == null) {
            return commentRepository.findCommentsByProduct_ProductIdAndParent_CommentId(productId, null, pageable);
        } else {
            return commentRepository.findCommentsByProduct_ProductIdAndParent_CommentId(productId, parent.getCommentId(), pageable);
        }
    }

    @Override
    public Page<Comment> getCommentsByProductIdWithUser(String userId, Comment parent, String productId, Pageable pageable) {
        if (parent != null) {
            return commentRepository.findCommentsByProduct_ProductIdAndParent_CommentIdAndStatusGreaterThanEqualAndOrderByCreateTimeAsc(productId,  parent.getCommentId(), 0, pageable);
        } else {
            return commentRepository.findCommentsByProduct_ProductIdAndParent_CommentIdAndStatusGreaterThanEqualAndOrderByCreateTimeAsc(productId, null, 0, pageable);
        }
    }

    @Override
    public Comment getCommentByCommentId(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment getCommentByCommentIdAndUserId(String commentId, String userId) {
        return commentRepository.findCommentByCommentIdAndAccount_UserIdAndStatusGreaterThanEqualAndOrderByCreateTimeAsc(commentId, userId, 0);
    }

    @Override
    public Comment createNewComment(Comment comment, Comment parentId, Account account, Product product) {
        comment.setAccount(account);
        comment.setUserId(account.getUserId());
        comment.setProduct(product);
        comment.setProductId(product.getProductId());
        comment.setParent(parentId);
        comment.setCreateTime(new Date());
        comment.setStatus(0);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment currentComment, Comment newComment) {
        newComment.setCommentId(currentComment.getCommentId());
        newComment.setAccount(currentComment.getAccount());
        newComment.setUserId(currentComment.getUserId());
        newComment.setProduct(currentComment.getProduct());
        newComment.setProductId(currentComment.getProductId());
        newComment.setParent(currentComment.getParent());
        newComment.setCreateTime(currentComment.getCreateTime());
        return commentRepository.save(newComment);
    }


    @Override
    public boolean changeStatusComment(Comment comment, int status) {
        comment.setStatus(status);
        commentRepository.save(comment);
        return true;
    }
}
