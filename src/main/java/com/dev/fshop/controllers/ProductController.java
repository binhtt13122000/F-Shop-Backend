package com.dev.fshop.controllers;

import com.dev.fshop.entities.Category;
import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Supplier;
import com.dev.fshop.services.CategoryService;
import com.dev.fshop.services.ProductDetailService;
import com.dev.fshop.services.ProductService;
import com.dev.fshop.services.SupplierService;
import com.dev.fshop.supporters.ProductDetail;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierService supplierService;

    @Operation(description = "get products", responses = {
            @ApiResponse(
                    description = "get products successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
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
    public ResponseEntity getProducts(
            @RequestParam Optional<String> q,
            @RequestParam Optional<String> productName,
            @RequestParam Optional<String> categoryName,
            @RequestParam Optional<Float> realPriceFrom,
            @RequestParam Optional<Float> realPriceTo,
            @RequestParam Optional<Integer> status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateTo,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize,
            Authentication authentication) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (q.isPresent()) {
            if (AuthenticatedRole.isAdmin(authentication)) {
                Page<Product> productList = productService.searchProductsByParameterQ(true, "%" + q.orElse(null) + "%", pageable);
                if (productList != null && !productList.isEmpty()) {
                    return new ResponseEntity(productList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                Page<Product> productList = productService.searchProductsByParameterQ(false, "%" + q.orElse(null) + "%", pageable);
                if (!productList.isEmpty() && productList != null) {
                    return new ResponseEntity(productList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            }
        } else {
            if (!productName.isPresent() && !categoryName.isPresent() && !realPriceFrom.isPresent() && !realPriceTo.isPresent()
                    && dateFrom == null && dateTo == null && !status.isPresent()) {
                if (AuthenticatedRole.isAdmin(authentication)) {
                    Page<Product> productList = productService.getProducts(true, pageable);
                    if (productList != null && !productList.isEmpty()) {
                        return new ResponseEntity(productList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Page<Product> productList = productService.getProducts(false, pageable);
                    if (productList != null && !productList.isEmpty()) {
                        return new ResponseEntity(productList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                }
            } else {
                if (AuthenticatedRole.isAdmin(authentication)) {
                    Page<Product> productList = productService.searchProductsByParameters(true, productName.orElse(null),
                            categoryName.orElse(null), realPriceFrom.orElse(null), realPriceTo.orElse(null),
                            dateFrom, dateTo, status.orElse(2), pageable);
                    if (productList != null && !productList.isEmpty()) {
                        return new ResponseEntity(productList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Page<Product> productList = productService.searchProductsByParameters(false, productName.orElse(null),
                            categoryName.orElse(null), realPriceFrom.orElse(null), realPriceTo.orElse(null),
                            dateFrom, dateTo, status.orElse(2), pageable);
                    if (productList != null && !productList.isEmpty()) {
                        return new ResponseEntity(productList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                }
            }
        }
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
    public ResponseEntity getProductById(@PathVariable String productId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Product product = productService.getProductByProductId(productId);
            if (product != null) {
                return new ResponseEntity(product, HttpStatus.OK);
            }
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        } else {
            Product product = productService.getProductByProductId(productId);
            if (product != null) {
                return new ResponseEntity(product, HttpStatus.OK);
            }
            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
        }
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
                    description = "Supplier or Category or Product is not available!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Supplier or Category or Product is not available!",
                                    value = "Supplier or Category or Product is not available!"
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
    public ResponseEntity updateProduct(@PathVariable String productId, @RequestBody Product product, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Product currentProduct = productService.getProductByProductId(productId);
            if (currentProduct != null) {
                Category checkCategoryExisted = categoryService.findCategoryByCategoryId(product.getCategoryId());
                if (checkCategoryExisted != null) {
                    Supplier checkSupplierExisted = supplierService.findSupplierBySupplierId(product.getSupplierId());
                    if (checkSupplierExisted != null) {
                        product.setCategory(checkCategoryExisted);
                        product.setSupplier(checkSupplierExisted);
                        productService.updateProduct(currentProduct, product);
                        return new ResponseEntity("update product successfully!", HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Supplier is not available!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Category is not available!", HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity("Product is not available!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
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
    public ResponseEntity addQuantity(@PathVariable String productId, @PathVariable String productSize, @PathVariable Integer quantity, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Product checkProductExisted = productService.getProductByProductId(productId);
            if (checkProductExisted != null) {
                ProductDetail productDetail = productDetailService.getProductDetailByProductIdAndProductSize(productId, productSize, 0);
                if (productDetail != null) {
                    if(productDetail.getProQuantity() != 1) {
                        productDetailService.addQuantity(productDetail, quantity);
                        return new ResponseEntity("add quantity successfully!", HttpStatus.OK);
                    }else {
                        productDetailService.addQuantity(productDetail, quantity);
                        productDetailService.changeStatusProductDetail(productDetail, 1);
                        if(checkProductExisted.getStatus() == 0) {
                            checkProductExisted.setStatus(1);
                            productService.changeStatusProductByProductId(checkProductExisted);
                        }
                        return new ResponseEntity("add quantity successfully!", HttpStatus.OK);
                    }
                }
                return new ResponseEntity("add quantity failed!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("product is not available!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "Delete product detail", responses = {
            @ApiResponse(
                    description = "Delete product detail successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Delete product detail successfully!",
                                    value = "Delete product detail successfully!"
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
                    description = "Delete product details failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Delete product details failed!",
                                    value = "Delete product details failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @DeleteMapping("/products/productDetails/{productDetailId}")
    public ResponseEntity deleteProductDetailByProductDetailId(@PathVariable String productDetailId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
                ProductDetail productDetail = productDetailService.getProductDetailByProductDetailId(productDetailId);
                if (productDetail != null && productDetail.getStatus() >= 0) {
                   boolean checkDelete =  productDetailService.changeStatusProductDetail(productDetail, -1);
                    if(checkDelete) {
                        return new ResponseEntity("Delete product detail successful.", HttpStatus.OK);
                    }
                    return new ResponseEntity("Delete product detail failed!", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity("Product Detail can not found by product detail id!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "Active product detail", responses = {
            @ApiResponse(
                    description = "Active product detail successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Active product detail successfully!",
                                    value = "Active product detail successfully!"
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
                    description = "Active product details failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Active product details failed!",
                                    value = "Active product details failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PatchMapping("/products/productDetails/{productDetailId}")
    public ResponseEntity activeProductDetailByProductDetailId(@PathVariable String productDetailId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            System.out.println(123);
            ProductDetail productDetail = productDetailService.getProductDetailByProductDetailId(productDetailId);
            System.out.println(productDetail);
            if (productDetail != null) {
                int status = -1;
                if(productDetail.getProQuantity() == 0) {
                    status = 0;
                }else {
                    status = 1;
                }
                boolean checkDelete =  productDetailService.changeStatusProductDetail(productDetail, status);
                if(checkDelete) {
                    return new ResponseEntity("Active product detail successful.", HttpStatus.OK);
                }
                return new ResponseEntity("Active product detail failed!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Product Detail can not found by product detail id!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
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
    public ResponseEntity createProduct(@RequestBody Product product, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Category checkCategoryExisted = categoryService.findCategoryByCategoryId(product.getCategoryId());
            if (checkCategoryExisted != null) {
                Supplier checkSupplierExisted = supplierService.findSupplierBySupplierId(product.getSupplierId());
                if (checkSupplierExisted != null) {
                    product.setCategory(checkCategoryExisted);
                    product.setSupplier(checkSupplierExisted);
                    productService.createNewProduct(product);
                    return new ResponseEntity("Create new product successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Supplier is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Category is not available!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "Delete product", responses = {
            @ApiResponse(
                    description = "Delete product is successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Delete product is successfully!",
                                    value = "Delete product is successfully!"
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
                    description = "Product is not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Product is not found!",
                                    value = "Product is not found!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Delete product failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Delete product failed!",
                                    value = "Delete product is failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProductByProductId(@PathVariable String productId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Product checkProductExisted = productService.getProductByProductId(productId);
            if (checkProductExisted != null && checkProductExisted.getStatus() != -1) {
                checkProductExisted.setStatus(-1);
                productService.changeStatusProductByProductId(checkProductExisted);
                return new ResponseEntity("Delete product is successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "Active product", responses = {
            @ApiResponse(
                    description = "Active product is successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Active product is successfully!",
                                    value = "Active product is successfully!"
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
                    description = "Product is not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Product is not found!",
                                    value = "Product is not found!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    description = "Active product failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Active product failed!",
                                    value = "Active product is failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PatchMapping("/products/{productId}")
    public ResponseEntity activeProductProductId(@PathVariable String productId, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Product checkProductExisted = productService.getProductByProductId(productId);
            if (checkProductExisted != null && checkProductExisted.getStatus() == -1) {
                boolean checkProductDetailIsNotOutOfStock = productDetailService.checkProductDetailIsNotOutOfStockByProductId(checkProductExisted.getProductId(), 1, 0);
                int status = -1;
                if(checkProductDetailIsNotOutOfStock) {
                    status = 1;
                }else {
                    status = 0;
                }
                checkProductExisted.setStatus(status);
                productService.changeStatusProductByProductId(checkProductExisted);
                return new ResponseEntity(checkProductExisted, HttpStatus.OK);
            } else {
                return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "Create new product details", responses = {
            @ApiResponse(
                    description = "Create new product details successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create new product details successfully!",
                                    value = "Create new product details successfully!"
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
                    description = "Create new product details failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Create product details failed!",
                                    value = "Create product details failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            ),
    })
    @PostMapping("/products/{productId}/productDetails")
    public ResponseEntity createProductDetails(@PathVariable String productId, @RequestBody ProductDetail productDetail, Authentication authentication) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            if(productId.equals(productDetail.getProductId())) {
                Product checkProduct = productService.getProductByProductId(productId);
                if (checkProduct != null) {
                    if(checkProduct.getStatus() != 1) {
                        checkProduct.setStatus(1);
                        productService.changeStatusProductByProductId(checkProduct);
                    }
                    ProductDetail checkProductDetail = productDetailService.getProductDetailByProductIdAndProductSize(checkProduct.getProductId(), productDetail.getProSize(), -1);
                    if (checkProductDetail != null) {
                        return new ResponseEntity(productDetailService.addQuantity(checkProductDetail, productDetail.getProQuantity()), HttpStatus.OK);
                    } else {

                        return new ResponseEntity(productDetailService.createNewProductDetail(checkProduct, productDetail.getProSize(), productDetail.getProQuantity()), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
                }
            }
            else {
                return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
