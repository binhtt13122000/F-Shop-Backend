package com.dev.fshop.controller;

import com.dev.fshop.entities.CommentEntity;
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
    @CrossOrigin
    public ResponseEntity<List<CommentEntity>> findCommentByProductId(@PathVariable String productId) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByProductId(productId));
    }

    @PostMapping(path = "/comments")
    @CrossOrigin
    public ResponseEntity<CommentEntity> createNewComment(@RequestBody CommentEntity comment) {
        return ResponseEntity.ok().body(commentServiceInterface.createNewComment(comment));
    }

    @PatchMapping(path = "/comments/{commentId}")
    @CrossOrigin
    public ResponseEntity<CommentEntity> updateCommentContent(@PathVariable String commentId, @RequestBody CommentEntity comment) {
        return ResponseEntity.ok().body(commentServiceInterface.updateCommentContent(comment, commentId));
    }

    @DeleteMapping(path = "/comments/{commentId}")
    @CrossOrigin
    public boolean deleteCommentByCommentId(@PathVariable String commentId) {
        return commentServiceInterface.deleteComment(commentId);
    }
}
