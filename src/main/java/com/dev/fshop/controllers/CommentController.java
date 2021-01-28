package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Product;
import com.dev.fshop.services.AccountService;
import com.dev.fshop.services.CommentService;
import com.dev.fshop.services.ProductService;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Comment")
public class CommentController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AccountService accountService;

    @Operation(description = "get comments by product", responses = {
            @ApiResponse(
                    description = "get comments by product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
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
    public ResponseEntity getCommentByProduct(@PathVariable("productId") String productId,
                                              @RequestParam Optional<String> username,
                                              @RequestParam Optional<Integer> pageIndex,
                                              @RequestParam Optional<Integer> pageSize,
                                              @RequestParam Optional<String> parentId,
                                              Authentication authentication) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        String usName = username.orElse(null);
        Comment parent = null;
        if (usName != null && AuthenticatedRole.isMySelf(usName, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(usName);
            if (checkAccountExisted == null) {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            } else {
                Product checkProductExisted = productService.getProductByProductId(productId);
                if (checkProductExisted == null) {
                    return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
                } else {
                    if (parentId.isPresent()) {
                        parent = commentService.getCommentByCommentId(parentId.orElse(null));
                    }
                    Page<Comment> commentList = commentService.getCommentsByProductIdWithUser(checkAccountExisted.getUserId(), parent,
                            checkProductExisted.getProductId(), pageable);
                    if (commentList.isEmpty() || commentList == null) {
                        return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                    }
                    return new ResponseEntity(commentList, HttpStatus.OK);
                }
            }
        } else if (AuthenticatedRole.isAdmin(authentication)) {
            Product checkProductExisted = productService.getProductByProductId(productId);
            if (checkProductExisted == null) {
                return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
            } else {
                if (parentId.isPresent()) {
                    parent = commentService.getCommentByCommentId(parentId.orElse(null));
                }
                Page<Comment> commentList = commentService.getCommentsByProductIdWithAdmin(checkProductExisted.getProductId(), parent, pageable);
                if (!commentList.isEmpty() && commentList != null) {
                    return new ResponseEntity(commentList, HttpStatus.OK);
                }
                return new ResponseEntity("NOT FOUND", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
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
                    description = "Not Found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Not Found!",
                                    value = "Not Found!"
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
                                    description = "Access denied",
                                    value = "Access denied!"
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
    @PostMapping("/products/{productId}/users/{username}/comments")
    public ResponseEntity postComment(@PathVariable("productId") String productId,
                                      @PathVariable("username") String username,
                                      @RequestParam Optional<String> parentId,
                                      @RequestBody Comment comment,
                                      Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Product checkProductExisted = productService.getProductByProductId(productId);
                if (checkProductExisted != null) {
                    if (parentId.isPresent()) {
                        Comment parentComment = commentService.getCommentByCommentId(parentId.orElse(null));
                        if (parentComment != null) {
                            commentService.createNewComment(comment, parentComment, checkAccountExisted, checkProductExisted);
                            return new ResponseEntity("Create new comment successfully!", HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Parent Comment is not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        commentService.createNewComment(comment, null, checkAccountExisted, checkProductExisted);
                        return new ResponseEntity("Create new comment successfully!", HttpStatus.OK);
                    }

                } else {
                    return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
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
                                    description = "Update comment successfully!",
                                    value = "Update comment successfully!"
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
    @PutMapping("/users/{username}/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable("commentId") String commentId,
                                        @PathVariable("username") String username,
                                        @RequestBody Comment comment,
                                        Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Comment checkCommentExisted = commentService.getCommentByCommentId(commentId);
                if (checkCommentExisted != null) {
                    if (checkCommentExisted.getStatus() == -1) {
                        return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                    } else {
                        commentService.updateComment(checkCommentExisted, comment);
                        return new ResponseEntity("Update comment successfully!", HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
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
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") String commentId,
                                        @RequestParam Optional<String> username,
                                        Authentication authentication) {
        String usName = username.orElse(null);
        if (usName != null && AuthenticatedRole.isMySelf(usName, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(usName);
            if (checkAccountExisted != null) {
                Comment checkCommentExisted = commentService.getCommentByCommentIdAndUserId(commentId, checkAccountExisted.getUserId());
                if (checkCommentExisted != null) {
                    if (checkCommentExisted.getStatus() == -1) {
                        return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                    } else {
                        commentService.changeStatusComment(checkCommentExisted, -1);
                        return new ResponseEntity("delete comment successfully!", HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else if (AuthenticatedRole.isAdmin(authentication)) {
            Comment checkCommentExisted = commentService.getCommentByCommentId(commentId);
            if (checkCommentExisted != null) {
                if (checkCommentExisted.getStatus() == -1) {
                    return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                } else {
                    commentService.changeStatusComment(checkCommentExisted, -1);
                    return new ResponseEntity("delete comment successfully!", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
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
    @PutMapping("/comments/{commentId}")
    public ResponseEntity confirmComment(@PathVariable("commentId") String commentId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Comment checkExisted = commentService.getCommentByCommentId(commentId);
            if (checkExisted != null) {
                if (checkExisted.getStatus() != -1) {
                    commentService.changeStatusComment(checkExisted, 1);
                    return new ResponseEntity("confirm comment successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity("Comment is not found!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }

    }

}
