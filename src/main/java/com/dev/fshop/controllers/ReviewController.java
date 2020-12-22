package com.dev.fshop.controllers;

import com.dev.fshop.entities.Review;
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

    //get reviews by product
    @Operation(description = "See reviews of product (ALL ROLE)", responses = {
            @ApiResponse(
                    description = "Get Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Review.class)))
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
    @GetMapping(path = "/products/{productId}/reviews")
    public ResponseEntity<List<Review>> findReviewByProductId(@PathVariable String productId) {
        return  ResponseEntity.ok().body(reviewService.findReviewByProductId(productId));
    }

    //create new Comment
    @RolesAllowed("ROLE_CUSTOMER")
    @Operation(description = "Create new Review (CUSTOMER)", responses = {
            @ApiResponse(
                    description = "Create Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when add review successfully!",
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
    @PostMapping(path = "/reviews")
    public ResponseEntity<Review> createNewReview(
            @RequestBody
            @Valid
            @Parameter(
                    description = "Review model to create.",
                    required = true,
                    schema = @Schema(implementation = Review.class)
            ) Review review
    ) {
        return ResponseEntity.ok().body(reviewService.createNewReview(review));
    }

    //update review
    @Operation(description = "Edit Review", responses = {
            @ApiResponse(
                    description = "Edit Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when update review successfully!",
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
                                    description = "Display when this review is not found!",
                                    value = "Review is not found!"),
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
    @PutMapping(path = "/reviews")
    public ResponseEntity<Review> updateReviewContentStar(
            @RequestParam(name = "reviewId") String reviewId,
            Authentication authentication,
            @RequestBody
            @Valid
            @Parameter(
                    description = "Comment model to update.",
                    required = true,
                    schema = @Schema(implementation = Review.class)
            ) Review review) {
        return null;
    }

    @DeleteMapping(path = "/reviews/{reviewId}")
    public boolean deleteReviewByReviewId(@PathVariable String reviewId) {
        return  reviewService.deleteReview(reviewId);
    }
}
