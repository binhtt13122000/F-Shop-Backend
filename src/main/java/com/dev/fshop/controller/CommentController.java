package com.dev.fshop.controller;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.services.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class CommentController {
    @Autowired
    private CommentServiceInterface commentServiceInterface;

    @GetMapping(path = "/comments")
    public ResponseEntity<List<CommentEntity>> getAllComments() {
        return ResponseEntity.ok().body(commentServiceInterface.getAllComments());
    }

    @GetMapping(path = "/comments/{userId}")
    public ResponseEntity<List<CommentEntity>> findCommentsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByUserId(userId));
    }

    @GetMapping(path = "/comments/{userName}")
    public ResponseEntity<List<CommentEntity>> findCommentsByUserName(@PathVariable String userName) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByUsername(userName));
    }

    @GetMapping(path = "/comments/{commentId}")
    public ResponseEntity<CommentEntity> findCommentByCommentId(@PathVariable Integer commentId) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByCommentId(commentId));
    }

    @PostMapping(path = "/comments")
    public ResponseEntity<CommentEntity> createNewComment(@RequestBody CommentEntity commentEntity) {
        return ResponseEntity.ok().body(commentServiceInterface.createNewComment(commentEntity));
    }

    @PatchMapping(path = "/comments/{commentId}")
    public ResponseEntity<CommentEntity> updateCommentContent(@PathVariable Integer commentId, @RequestBody CommentEntity commentEntity) {
        return ResponseEntity.ok().body(commentServiceInterface.updateCommentContent(commentEntity.getContent(), commentId));
    }

    @DeleteMapping(path = "/comments/{commentId}")
    public boolean deleteCommentByCommentId(@PathVariable Integer commentId) {
        return commentServiceInterface.deleteComment(commentId);
    }
}
