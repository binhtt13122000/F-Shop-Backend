package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.entities.Product;
import com.dev.fshop.services.*;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.ProductDetail;
import com.dev.fshop.utils.AuthenticatedRole;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private AccountService accountService;

    @Operation(description = "get carts", responses = {
            @ApiResponse(
                    description = "get carts successfully!",
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
            )
    })
    @GetMapping("/carts/{username}")
    public ResponseEntity getCarts(
            @PathVariable String username,
            @RequestParam Optional<String> q,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateTo,
            @RequestParam Optional<Float> priceFrom,
            @RequestParam Optional<Float> priceTo,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize,
            Authentication authentication
    ) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (q.isPresent()) {
            if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
                Account checkAccountExisted = accountService.getUserByUsername(username);
                if (checkAccountExisted != null) {
                    Page<Cart> cartList = cartService.getCartsByParameterQ(checkAccountExisted.getUserId(), q.orElse(null), pageable);
                    if (cartList != null && !cartList.isEmpty()) {
                        return new ResponseEntity(cartList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
            }
        } else {
            if (dateFrom == null && dateTo == null && !priceFrom.isPresent() && !priceTo.isPresent()) {
                if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
                    Account checkAccountExisted = accountService.getUserByUsername(username);
                    if (checkAccountExisted != null) {
                        Page<Cart> cartList = cartService.getAllCarts(checkAccountExisted.getUserId(), pageable);
                        if (!cartList.isEmpty() && cartList != null) {
                            return new ResponseEntity(cartList, HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
                }
            } else {
                if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
                    Account checkAccountExisted = accountService.getUserByUsername(username);
                    if (checkAccountExisted != null) {
                        Page<Cart> cartList = cartService.getCartByParameters(checkAccountExisted.getUserId(),
                                dateFrom, dateTo, priceFrom.orElse(null), priceTo.orElse(null), pageable);
                        if (cartList != null && !cartList.isEmpty()) {
                            return new ResponseEntity(cartList, HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
                }
            }
        }
    }

    @Operation(description = "create new cart", responses = {
            @ApiResponse(
                    description = "create new cart successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "create new cart successfully!",
                                    value = "Create new cart successfully!"
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
                    description = "create new cart failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "create new cart failed!",
                                    value = "Create new cart failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/carts/users/{username}")
    public ResponseEntity createNewCart(@PathVariable String username, @RequestBody Cart cart, Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                cartService.createNewCart(checkAccountExisted, cart);
                return new ResponseEntity("Create new cart successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "add product in cart details", responses = {
            @ApiResponse(
                    description = "add product successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "add product successfully!",
                                    value = "Add product successfully!"
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
            @ApiResponse(
                    description = "add product failed",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "add product failed!",
                                    value = "Add product failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/carts/{cartId}/users/{username}/cartDetails")
    public ResponseEntity addProductInCartDetail(@PathVariable String cartId,
                                                 @PathVariable String username,
                                                 @RequestParam  String productId,
                                                 @RequestParam  String cartSize,
                                                 @RequestParam  Integer cartQuantity,
                                                 Authentication authentication
    ) {
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Product checkProductExisted = productService.getProductByProductId(productId);
                if (checkProductExisted != null) {
                    ProductDetail checkProductDetailExisted = productDetailService.getProductDetailByProductIdAndProductSize(productId, cartSize, 0);
                    if (checkProductDetailExisted != null) {
                        Cart checkCartExisted = cartService.getCartByCartIdAndUserId(cartId, checkAccountExisted.getUserId(), 0);
                        if (checkCartExisted != null) {
                            if (checkCartExisted.getStatus() == 1) {
                                CartDetail checkCartDetailExisted = cartDetailService.getCartDetailByCartIdAndProductIdAndCartSize(checkCartExisted.getCartId(),
                                        checkProductExisted.getProductId(), checkProductDetailExisted.getProSize());
                                if (checkCartDetailExisted != null) {
                                    System.out.println(cartQuantity);
                                    cartDetailService.addQuantityProductInCartDetailExisted(checkCartDetailExisted, checkProductExisted, cartQuantity);
                                    return new ResponseEntity("Add product successfully!", HttpStatus.OK);
                                } else {
                                    cartDetailService.addProductInCartDetail(checkAccountExisted, checkCartExisted, checkProductExisted,
                                            checkProductDetailExisted, cartQuantity);
                                    return new ResponseEntity("Add product successfully!", HttpStatus.OK);
                                }
                            } else {
                                return new ResponseEntity("Cart is not found!", HttpStatus.NOT_FOUND);
                            }
                        } else {
                            return new ResponseEntity("Cart is not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity("Product Detail is not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Product is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "get carts by username", responses = {
            @ApiResponse(
                    description = "get carts successfully!",
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
            )
    })
    @GetMapping("/carts/users/{username}")
    public ResponseEntity getCartsByUsername(@PathVariable String username,
                                             @RequestParam Optional<Integer> pageIndex,
                                             @RequestParam Optional<Integer> pageSize,
                                             Authentication authentication) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkExistedAccount = accountService.getUserByUsername(username);
            if (checkExistedAccount != null) {
                Page<Cart> cartList = cartService.getAllCarts(checkExistedAccount.getUserId(), pageable);
                if (!cartList.isEmpty() && cartList != null) {
                    return new ResponseEntity(cartList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Cart is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "get cart details", responses = {
            @ApiResponse(
                    description = "get cart details successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Page.class))
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
                    description = "Not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Not found!",
                                    value = "Not found!"),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/carts/{cartId}/users/{username}/cartDetails")
    public ResponseEntity getCartDetailsByCartId(@PathVariable String cartId,
                                                 @PathVariable String username,
                                                 Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Cart checkCartExisted = cartService.getCartByCartIdAndUserId(cartId, checkAccountExisted.getUserId(), 0);
                if (checkCartExisted != null) {
                    List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartIdAndUserId(cartId, checkAccountExisted.getUserId());
                    if (!cartDetailList.isEmpty() && cartDetailList != null) {
                        return new ResponseEntity(cartDetailList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Cart is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Can not found your account!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
