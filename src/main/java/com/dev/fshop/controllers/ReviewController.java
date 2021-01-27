package com.dev.fshop.controllers;

import com.dev.fshop.entities.*;
import com.dev.fshop.services.AccountService;
import com.dev.fshop.services.OrderService;
import com.dev.fshop.services.ProductService;
import com.dev.fshop.services.ReviewService;
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

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Operation(description = "get review by product", responses = {
            @ApiResponse(
                    description = "get review by product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Review.class))
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
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity getReviewsByProduct(@PathVariable("productId") String productId,
                                              @RequestParam Optional<String> username,
                                              @RequestParam Optional<Integer> pageIndex,
                                              @RequestParam Optional<Integer> pageSize,
                                              Authentication authentication) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        String usName = username.orElse(null);
        if (usName != null && AuthenticatedRole.isMySelf(usName, authentication) &&
                !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(usName);
            if (checkAccountExisted != null) {
                Product checkProductExisted = productService.getProductByProductId(productId);
                if (checkProductExisted != null) {
                    Page<Review> reviewList = reviewService.findReviewsByProductIdWithUserId(checkProductExisted.getProductId(), checkAccountExisted.getUserId(),
                            pageable);
                    if (!reviewList.isEmpty() && reviewList != null) {
                        return new ResponseEntity(reviewList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else if (AuthenticatedRole.isAdmin(authentication)) {
            Product checkProductExisted = productService.getProductByProductId(productId);
            if (checkProductExisted != null) {
                Page<Review> reviewList = reviewService.findReviewsByProductIdWithAdmin(checkProductExisted.getProductId(),
                        pageable);
                if (!reviewList.isEmpty() && reviewList != null) {
                    return new ResponseEntity(reviewList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    //create
    @Operation(description = "Create new review", responses = {
            @ApiResponse(
                    description = "Create new review successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new review successfully!",
                                    value = "Create new review successfully!"
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
                    description = "Product or Order is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Product or Order is not available!",
                                    value = "Product or Order is not available!"
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
    @PostMapping("/products/{productId}/users/{username}/reviews")
    public ResponseEntity postReview(@PathVariable("productId") String productId,
                                     @PathVariable("username") String username,
                                     @RequestBody Review review,
                                     Authentication authentication) {
        Pageable pageable = PageRequest.of(0, 4);
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Product checkProductExisted = productService.getProductByProductId(productId);
                if (checkProductExisted != null) {
                    List<Orders> ordersList = orderService.getOrdersByProductIdAndUserId(checkProductExisted.getProductId(),
                            checkAccountExisted.getUserId(), false);
                    if (!ordersList.isEmpty() && ordersList != null) {
                        reviewService.postReview(review, ordersList.get(ordersList.size() - 1), checkProductExisted);
                        return new ResponseEntity("Create new review successfully!", HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Order is not available!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Product is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    //update
    @Operation(description = "update reviews", responses = {
            @ApiResponse(
                    description = "update reviews successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update reviews successfully!",
                                    value = "update reviews successfully!"
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
                    description = "reviews is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "reviews is not available!",
                                    value = "reviews is not available!"
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
    @PutMapping("/users/{username}/reviews/{reviewId}")
    public ResponseEntity updateReview(@PathVariable("reviewId") String reviewId,
                                       @PathVariable("username") String userName,
                                       @RequestBody Review review,
                                       Authentication authentication) {
        if (AuthenticatedRole.isMySelf(userName, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(userName);
            if (checkAccountExisted != null) {
                Review checkReviewExisted = reviewService.findReviewByReviewIdWithUser(reviewId, checkAccountExisted.getUserId());
                if (checkReviewExisted != null) {
                    reviewService.updateReview(checkReviewExisted, review);
                    return new ResponseEntity("update reviews successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    //delete
    @Operation(description = "delete reviews", responses = {
            @ApiResponse(
                    description = "delete reviews successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "delete reviews successfully!",
                                    value = "delete reviews successfully!"
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
                    description = "reviews is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "reviews is not available!",
                                    value = "reviews is not available!"
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
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") String reviewId,
                                       @RequestParam Optional<String> userName,
                                       Authentication authentication) {
        String usName = userName.orElse(null);
        if (usName != null && AuthenticatedRole.isMySelf(usName, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(usName);
            if (checkAccountExisted != null) {
                Review checkReviewExisted = reviewService.findReviewByReviewIdWithUser(reviewId, checkAccountExisted.getUserId());
                if (checkReviewExisted != null) {
                    if (checkReviewExisted.getStatus() != -1) {
                        reviewService.changeStatusReview(checkReviewExisted, -1);
                        return new ResponseEntity("delete reviews successfully!", HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else if (AuthenticatedRole.isAdmin(authentication)) {
            Review checkReviewExisted = reviewService.findReviewByReviewIdWithAdmin(reviewId);
            if (checkReviewExisted != null) {
                if (checkReviewExisted.getStatus() != -1) {
                    reviewService.changeStatusReview(checkReviewExisted, -1);
                    return new ResponseEntity("delete reviews successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "confirm review", responses = {
            @ApiResponse(
                    description = "confirm review successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "confirm review successfully!",
                                    value = "confirm review successfully!"
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
                    description = "Review is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Review is not available!",
                                    value = "Review is not available!"
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
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity confirmReview(@PathVariable("reviewId") String reviewId,
                                        Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Review checkExisted = reviewService.findReviewByReviewIdWithAdmin(reviewId);
            if (checkExisted != null) {
                if (checkExisted.getStatus() != -1) {
                    reviewService.changeStatusReview(checkExisted, 1);
                    return new ResponseEntity("confirm review successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Review is not found!", HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity("Review is not available!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
