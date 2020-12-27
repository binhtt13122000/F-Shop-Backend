package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Comment;
import com.dev.fshop.entities.Promotion;
import com.dev.fshop.services.AccountService;
import com.dev.fshop.services.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Promotion")
public class PromotionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PromotionService promotionService;

    @Operation(description = "get promotion by user", responses = {
            @ApiResponse(
                    description = "get promotion by user successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Promotion.class))
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
                    description = "User is not available!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "User is not available!",
                                    value = "User is not available!"
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
    @GetMapping("/users/{username}/promotions")
    public ResponseEntity findPromotionByAccount(@PathVariable String username){
        Account account = accountService.getUserByUsername(username);
        if(account != null) {
            try {
                List<Promotion> promotionList = promotionService.getPromotionsByUserId(account.getUserId());
                if(promotionList != null && !promotionList.isEmpty()) {
                    return new ResponseEntity(promotionList, HttpStatus.OK);
                }else {
                    return new ResponseEntity("Promotion can not found", HttpStatus.NOT_FOUND);
                }
            }catch (Exception e) {
                return  new ResponseEntity("Promotion can not found", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("User is not available", HttpStatus.BAD_REQUEST);
    }

    @Operation(description = "Create new promotion", responses = {
            @ApiResponse(
                    description = "Create new promotion successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new promotion successfully!",
                                    value = "Create new promotion successfully!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Account is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Account is not available!",
                                    value = "Account is not available!"
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
    @PostMapping("/users/{username}/promotions")
    public ResponseEntity createPromotion(@PathVariable String username, @RequestBody Promotion promotion){
        Account account = accountService.getUserByUsername(username);
        if(account != null) {
            try {
                promotionService.createPromotion(promotion);
                return new ResponseEntity("Create new promotion successfully!", HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Create promotion failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Account is not available", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "update promotion", responses = {
            @ApiResponse(
                    description = "update promotion successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update promotion successfully!",
                                    value = "update promotion successfully!"
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
                    description = "Account is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Account is not available!",
                                    value = "Account is not available!"
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
    @PutMapping("/users/{username}/promotions")
    public ResponseEntity updatePromotion(@PathVariable String username, @RequestBody Promotion promotion){
        Account account = accountService.getUserByUsername(username);
        if(account != null) {
            try {
                promotionService.updatePromotion(promotion);
                return new ResponseEntity("Update promotion successfully!", HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Update Promotion failed!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Account is not available", HttpStatus.NOT_FOUND);
    }
}
