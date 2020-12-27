package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Product;
import com.dev.fshop.services.ProductDetailService;
import com.dev.fshop.services.ProductService;
import com.dev.fshop.supporters.ProductDetail;
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

    @Autowired
    private ProductDetailService productDetailService;

    @Operation(description = "get products", responses = {
            @ApiResponse(
                    description = "get products successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))
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
    @GetMapping("/products")
    public ResponseEntity getProducts(){
        List<Product> productList = productService.getProducts();
        if(!productList.isEmpty() && productList != null) {
            return new ResponseEntity(productList, HttpStatus.OK);
        }
        return new ResponseEntity("Not found products", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "get product by id", responses = {
            @ApiResponse(
                    description = "get products successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)
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
    @GetMapping("/products/{productId}")
    public ResponseEntity getProductById(@PathVariable String productId){
        Product product = productService.getProductByProId(productId);
        if(product != null) {
            return new ResponseEntity(product,HttpStatus.OK);
        }
        return new ResponseEntity("Product is not available", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "update product", responses = {
            @ApiResponse(
                    description = "update product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "update product successfully!",
                                    value = "update product successfully!"
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
                    description = "product is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "product is not available!",
                                    value = "product is not available!"
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
    @PutMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable String productId, @RequestBody Product product){
        Product checkExisted = productService.getProductByProId(productId);
        if(checkExisted != null) {
            try {
                return new ResponseEntity(productService.updateProduct(product), HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Update Product failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Product is not available", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "add quantity", responses = {
            @ApiResponse(
                    description = "add quantity successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "add quantity successfully!",
                                    value = "add quantity successfully!"
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
                    description = "product is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "product is not available!",
                                    value = "product is not available!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "add quantity failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "add quantity failed!",
                                    value = "add quantity failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PutMapping("/products/{productId}/{productSize}/{quantity}")
    public ResponseEntity addQuantity(@PathVariable String productId, @PathVariable String productSize, @PathVariable Integer quantity){
        ProductDetail productDetail = productDetailService.getProductDetailByProIdAndProSize(productId, productSize);
        if(productDetail != null) {
            try {
                if(quantity <= 0) {
                    return new ResponseEntity("Quantity must be greater than 0", HttpStatus.BAD_REQUEST);
                }else {
                    productDetail.setProQuantity(productDetail.getProQuantity() + quantity);
                    productDetailService.addQuantity(productDetail);
                    return new ResponseEntity("Add quantity product successful.", HttpStatus.OK);
                }
            }catch (Exception e) {
                return new ResponseEntity("Add quantity product failed!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Product Detail with product id and product size is not available", HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Create new product", responses = {
            @ApiResponse(
                    description = "Create new product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new product successfully!",
                                    value = "Create new product successfully!"
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
                    description = "Supplier or Category is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Supplier or Category is not available!",
                                    value = "Supplier or Category is not available!"
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
    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody Product product){
        Product checkExisted = productService.getProductByProId(product.getProId());
        if(checkExisted == null) {
            try {
                return new ResponseEntity(productService.createNewProduct(product), HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity("Update Product failed", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Product is existed", HttpStatus.NOT_FOUND);
    }


}
