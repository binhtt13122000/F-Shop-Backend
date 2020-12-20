package com.dev.fshop.controllers;

import com.dev.fshop.entities.CommentEntity;
import com.dev.fshop.entities.PromotionEntity;
import com.dev.fshop.services.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    //get promotion by user
    @Operation(description = "See promotions of user (ALL ROLE)", responses = {
            @ApiResponse(
                    description = "Get Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CommentEntity.class)))
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
                                    description = "Display when this user is not found!",
                                    value = "User is not found!"),
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
    @GetMapping(path = "/promotions")
    public ResponseEntity<List<PromotionEntity>> getAllPromotionsByUserId(@RequestParam(name = "userId") String userId) {
        return  ResponseEntity.ok().body(promotionService.getAllPromotionsByUserId(userId));
    }


}
