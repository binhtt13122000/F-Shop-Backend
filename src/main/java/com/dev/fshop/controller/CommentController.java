package com.dev.fshop.controller;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.services.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class CommentController {
    @Autowired
    private CommentServiceInterface commentServiceInterface;

    @GetMapping(path = "/comments")
    public List<CommentEntity> getAllComments() {
        return commentServiceInterface.getAllComments();
    }

    @GetMapping(path = "/comments/{userId}")
    public List<CommentEntity> findCommentsByUserId(@PathVariable String userId) {
        return commentServiceInterface.findCommentByUserId(userId);
    }

    @GetMapping(path = "/comments/{userName}")
    public List<CommentEntity> findCommentsByUserName(@PathVariable String userName) {
        return commentServiceInterface.findCommentByUsername(userName);
    }

    @GetMapping(path = "/comments/{commentId}")
    public CommentEntity findCommentByCommentId(@PathVariable Integer commentId) {
        return commentServiceInterface.findCommentByCommentId(commentId);
    }

    @PostMapping(path = "/comments")
    public CommentEntity createNewComment(@RequestBody CommentEntity commentEntity) {
        return commentServiceInterface.createNewComment(commentEntity);
    }

    @PatchMapping(path = "/comments/{commentId}")
    public CommentEntity updateCommentContent(@PathVariable Integer commentId, @RequestBody CommentEntity commentEntity) {
        return commentServiceInterface.updateCommentContent(commentEntity.getContent(), commentId);
    }

    @DeleteMapping(path = "/comments/{commentId}")
    public boolean deleteCommentByCommentId(@PathVariable Integer commentId) {
        return commentServiceInterface.deleteComment(commentId);
    }
}
