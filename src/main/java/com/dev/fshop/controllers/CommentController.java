package com.dev.fshop.controllers;

import com.dev.fshop.entity.CommentEntity;
import com.dev.fshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/v1/api")
public class CommentController {
//    @Autowired
//    private CommentService commentServiceInterface;
//
//    @GetMapping(path = "/products/{productId}/comments")
//    public ResponseEntity<List<CommentEntity>> findCommentByProductId(@PathVariable String productId) {
//        return ResponseEntity.ok().body(commentServiceInterface.findCommentByProductId(productId));
//    }
//
//    @PostMapping(path = "/comments")
//    public ResponseEntity<CommentEntity> createNewComment(@RequestBody CommentEntity comment) {
//        return ResponseEntity.ok().body(commentServiceInterface.createNewComment(comment));
//    }
//
//    @PatchMapping(path = "/comments/{commentId}")
//    public ResponseEntity<CommentEntity> updateCommentContent(@PathVariable String commentId, @RequestBody CommentEntity comment) {
//        return ResponseEntity.ok().body(commentServiceInterface.updateCommentContent(comment, commentId));
//    }
//
//    @DeleteMapping(path = "/comments/{commentId}")
//    public boolean deleteCommentByCommentId(@PathVariable String commentId) {
//        return commentServiceInterface.deleteComment(commentId);
//    }
}
