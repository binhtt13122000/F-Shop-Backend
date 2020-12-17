package com.dev.fshop.controller;

import com.dev.fshop.embedded.Comment;
import com.dev.fshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/v1/api")
public class CommentController {
    @Autowired
    private CommentService commentServiceInterface;

    @GetMapping(path = "/products/{productId}/comments")
    public ResponseEntity<List<Comment>> findCommentByProductId(@PathVariable String productId) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByProductId(productId));
    }

    @PostMapping(path = "/comments")
    public ResponseEntity<Comment> createNewComment(@RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentServiceInterface.createNewComment(comment));
    }

    @PatchMapping(path = "/comments/{commentId}")
    public ResponseEntity<Comment> updateCommentContent(@PathVariable String commentId, @RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentServiceInterface.updateCommentContent(comment, commentId));
    }

    @DeleteMapping(path = "/comments/{commentId}")
    public boolean deleteCommentByCommentId(@PathVariable String commentId) {
        return commentServiceInterface.deleteComment(commentId);
    }
}
