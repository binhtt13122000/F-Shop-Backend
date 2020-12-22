package com.dev.fshop.controllers;

import com.dev.fshop.entities.Product;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //create new Product
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SELLER"})
    @Operation(description = "Create new Comment (ADMIN, SELLER)", responses = {
            @ApiResponse(
                    description = "Create Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when add product successfully!",
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
                                    description = "Display when type is not exist",
                                    value = "Type is not existed!"),
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
    @PostMapping(path = "/products")
    public ResponseEntity<Product> createNewProduct(
            @RequestBody
            @Valid
            @Parameter(
                    description = "Product model to create.",
                    required = true,
                    schema = @Schema(implementation = Product.class)
            ) Product product) {
        return ResponseEntity.ok().body(productService.createNewProduct(product));
    }

    //update comment
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SELLER"})
    @Operation(description = "Edit Product (ADMIN, SELLER)", responses = {
            @ApiResponse(
                    description = "Edit Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when update product successfully!",
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
                    description = "Create failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when type is not existed!",
                                    value = "Type is not existed!"),
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
    @PutMapping(path = "/products/{proId}")
    public ResponseEntity<Product> updateProductExisted(
            @PathVariable String proId,
            @RequestBody
            @Valid
            @Parameter(
                    description = "Product model to update.",
                    required = true,
                    schema = @Schema(implementation = Product.class)
            ) Product product) {
        return ResponseEntity.ok().body(productService.updateProductExisted(proId, product));
    }

    //delete product
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SELLER"})
    @Operation(description = "Delete Product (ADMIN, SELLER)", responses = {
            @ApiResponse(
                    description = "Delete Successfully",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "String",
                            examples = @ExampleObject(
                                    description = "Display when delete product successfully!",
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
    @DeleteMapping(path = "/products/{proId}")
    public ResponseEntity deleteProductExisted(@PathVariable String proId) {
        productService.deleteProductExisted(proId);
        return null;
    }


    @Operation(description = "See products (ALL ROLE)", responses = {
            @ApiResponse(
                    description = "Get Successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))
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
    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> findProducts(
            @RequestParam Optional<Integer> star,
            @RequestParam Optional<Date> createAt,
            @RequestParam Optional<String> type,
            @RequestParam(name = "priceFrom") float priceFrom, @RequestParam(name = "priceTo") float priceTo,
            @RequestParam Optional<String> proId,
            @RequestParam Optional<String> proName) {
        return null;
    }



}
