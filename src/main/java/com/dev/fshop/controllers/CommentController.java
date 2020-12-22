package com.dev.fshop.controllers;

import com.dev.fshop.entities.Comment;
import com.dev.fshop.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Comment")
public class CommentController {
    @Autowired
    private CommentService commentServiceInterface;


    //get comments by product
    @Operation(description = "See comments of product (ALL ROLE)", responses = {
            @ApiResponse(
                    description = "Get Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when this product is not found!",
                                    value = "Product is not found!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(path = "/products/{productId}/comments")
    public ResponseEntity<List<Comment>> findCommentByProductId(@PathVariable String productId) {
        return ResponseEntity.ok().body(commentServiceInterface.findCommentByProductId(productId));
    }

    //create new Comment
    @Operation(description = "Create new Comment (ALL ROLE)", responses = {
            @ApiResponse(
                    description = "Create Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when add comment successfully!",
                                    value = "Create Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Create failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user or product is not existed!",
                                    value = "User or Product is not existed!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(path = "/comments")
    public ResponseEntity createNewComment(
            @RequestBody
            @Valid
            @Parameter(
                    description = "Comment model to create.",
                    required = true,
                    schema = @Schema(implementation = Comment.class)
            ) Comment comment) {
            return ResponseEntity.ok().body(commentServiceInterface.createNewComment(comment));
    }

    //update comment
    @Operation(description = "Edit Comment", responses = {
            @ApiResponse(
                    description = "Edit Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when update comment successfully!",
                                    value = "Update Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Update failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user or product is not existed!",
                                    value = "User or Product is not existed!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when this comment is not found!",
                                    value = "Comment is not found!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @PatchMapping(path = "/comments/{commentId}")
    public ResponseEntity<Comment> updateCommentContent(
            @PathVariable String commentId,
            @RequestBody
            @Valid
            @Parameter(
                    description = "Comment model to update.",
                    required = true,
                    schema = @Schema(implementation = Comment.class)
            ) Comment comment,
            Authentication authentication) {
        return ResponseEntity.ok().body(commentServiceInterface.updateCommentContent(comment, commentId));
    }

    //delete comment
    @Operation(description = "Delete Comment", responses = {
            @ApiResponse(
                    description = "Delete Successfully",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when delete comment successfully!",
                                    value = "Delete Successfully"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Access deny!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when user is not authorized!",
                                    value = "Access deny!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when this comment is not found!",
                                    value = "Comment is not found!"),
                            schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    description = "Server throw Exception!",
                    responseCode = "500",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Server throw Exception!",
                                    value = "Server throw Exception!"),
                            schema = @Schema(implementation = String.class))
            )
    })
    @DeleteMapping(path = "/comments/{commentId}")
    public boolean deleteCommentByCommentId(@PathVariable String commentId, Authentication authentication) {
        return commentServiceInterface.deleteComment(commentId);
    }
}
