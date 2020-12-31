package com.dev.fshop.controllers;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Review;
import com.dev.fshop.services.OrderService;
import com.dev.fshop.services.ProductService;
import com.dev.fshop.services.ReviewService;
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

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity getReviewsByProduct(@PathVariable("productId") String productId) {
        List<Review> reviewList = reviewService.findReviewsByProductId(productId);
        if (!reviewList.isEmpty() && reviewList != null) {
            return new ResponseEntity(reviewList, HttpStatus.OK);
        }
        return new ResponseEntity("Can not find list reviews by product id", HttpStatus.NOT_FOUND);
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
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity postReview(@PathVariable("productId") String productId, @RequestBody Review review) {
        Product product = productService.getProductByProductId(productId);
        if (product != null) {
            Orders orders = orderService.findOrderByOrderId(review.getOrderId());
            if (orders != null) {
                List<Review> reviewList = reviewService.findReviewsByProductId(productId);
                if (!reviewList.isEmpty() && reviewList != null) {
                    return new ResponseEntity(reviewList, HttpStatus.OK);
                }
                return new ResponseEntity("Can not find list reviews by product id", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity("Order is not available", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Product is not available", HttpStatus.NOT_FOUND);
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
    @PutMapping("/reviews/{reviewId}/update")
    public ResponseEntity updateReview(@PathVariable("reviewId") String reviewId, @RequestBody Review review) {
        Review checkExisted = reviewService.findReviewByReviewId(reviewId);
        if (checkExisted != null) {
            try {
                reviewService.updateReview(review);
                return new ResponseEntity("Update review successful", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity("Update review failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Review is not available", HttpStatus.NOT_FOUND);
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
    @PutMapping("/reviews/{reviewId}/delete")
    public ResponseEntity deleteReview(@PathVariable("reviewId") String reviewÌd) {
        Review checkExisted = reviewService.findReviewByReviewId(reviewÌd);
        if (checkExisted != null) {
            try {
                reviewService.deleteReview(reviewÌd);
                return new ResponseEntity("Delete review successful", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity("Delete review failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Review is not available", HttpStatus.NOT_FOUND);
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
                    description = "review is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "review is not available!",
                                    value = "review is not available!"
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
    @PutMapping("/reviews/{reviewId}/confirm")
    public ResponseEntity confirmReview(@PathVariable("reviewId") String reviewId) {
        Review checkExisted = reviewService.findReviewByReviewId(reviewId);
        if (checkExisted != null) {
            try {
                boolean checkConfirm = reviewService.confirmReview(checkExisted);
                if (checkConfirm) {
                    return new ResponseEntity("Confirm review successful", HttpStatus.OK);
                }
                return new ResponseEntity("Confirm review failed", HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity("Confirm review failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Review is not available", HttpStatus.NOT_FOUND);
    }
}
