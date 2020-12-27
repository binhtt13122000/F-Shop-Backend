package com.dev.fshop.controllers;

import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Product;
import com.dev.fshop.services.CommentService;
import com.dev.fshop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ProductService productService;

    @Autowired
    private CommentService commentService;

    @Operation(description = "get comments by product", responses = {
            @ApiResponse(
                    description = "get comments by product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class))
                    )
            ),
            @ApiResponse(
                    description = "Access denied!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied!",
                                    value = "Access denied!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Product is not available!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Product is not available!",
                                    value = "Product is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Not found!",
                                    value = "Not found!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @GetMapping("/products/{productId}/comments")
    public ResponseEntity getCommentByProduct(@PathVariable("productId") String productId) {
            try {
                List<Comment> commentList = commentService.getCommentsByProductId(productId);
                if (!commentList.isEmpty() && commentList != null) {
                    return new ResponseEntity(commentList, HttpStatus.OK);
                }
                return new ResponseEntity("Get list comments by product id failed!", HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity("Get list comments by product id failed!", HttpStatus.NOT_FOUND);
            }
    }

    //create
    @Operation(description = "Create new comment", responses = {
            @ApiResponse(
                    description = "Create new comment successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new comment successfully!",
                                    value = "Create new comment successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Product is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Product is not available!",
                                    value = "Product is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Create failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create failed!",
                                    value = "Create failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping("/products/{productId}/comments")
    public ResponseEntity postComment(@PathVariable("productId") String productId, @RequestBody Comment comment) {
            try {
                commentService.createNewComment(comment);
                return new ResponseEntity("Post comment successful", HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Post comment failed.", HttpStatus.BAD_REQUEST);
            }
    }

    //update
    @Operation(description = "update comment", responses = {
            @ApiResponse(
                    description = "update comment successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update comment successfully!",
                                    value = "update comment successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Access denied!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied!",
                                    value = "Access denied!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Comment is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Comment is not available!",
                                    value = "Comment is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Update failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Update failed!",
                                    value = "Update failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/comments/{commentId}/update")
    public ResponseEntity updateComment(@PathVariable("commentId") String commentId, @RequestBody Comment comment) {
        Comment checkExisted = commentService.getCommentByCommentId(commentId);
        if(checkExisted != null) {
            try {
                return new ResponseEntity(commentService.updateComment(comment), HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Update comment failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
    }

    //delete
    @Operation(description = "delete comment", responses = {
            @ApiResponse(
                    description = "delete comment successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "delete comment successfully!",
                                    value = "delete comment successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Access denied!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied!",
                                    value = "Access denied!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Comment is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Comment is not available!",
                                    value = "Comment is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "delete failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "delete failed!",
                                    value = "delete failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/comment/{commentId}/delete")
    public ResponseEntity deleteComment(@PathVariable("commentId") String commentId) {
        Comment comment = commentService.getCommentByCommentId(commentId);
        if(comment != null) {
            boolean check = commentService.deleteComment(commentId);
            if (check) {
                return new ResponseEntity("Delete Comment successful", HttpStatus.OK);
            }
            return new ResponseEntity("Delete Comment failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "confirm comment", responses = {
            @ApiResponse(
                    description = "confirm comment successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "confirm comment successfully!",
                                    value = "confirm comment successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Access denied!",
                    responseCode = "403",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Access denied!",
                                    value = "Access denied!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Comment is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Comment is not available!",
                                    value = "Comment is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "confirm failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "confirm failed!",
                                    value = "confirm failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/comments/{commentId}/confirm")
    public ResponseEntity confirmComment(@PathVariable("commentId") String commentId) {
        Comment checkExisted = commentService.getCommentByCommentId(commentId);
        if(checkExisted != null) {
            try {
                return new ResponseEntity(commentService.updateComment(checkExisted), HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Confirm comment failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("CommentId is not found", HttpStatus.NOT_FOUND);
    }

}
