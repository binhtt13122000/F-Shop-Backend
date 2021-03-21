package com.dev.fshop.controllers;

import com.dev.fshop.entities.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.transaction.TransactionScoped;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductService productService;

    @Operation(description = "get orders", responses = {
            @ApiResponse(
                    description = "get orders successfully!",
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
    @GetMapping("/orders")
    public ResponseEntity getOrders(
            @RequestParam Optional<String> username,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date dateTo,
            @RequestParam Optional<Float> priceFrom,
            @RequestParam Optional<Float> priceTo,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize,
            Authentication authentication
    ) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (AuthenticatedRole.isAdmin(authentication)) {
            if (username.isPresent()) {
                Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
                if (checkAccountExisted != null) {
                    if (dateFrom == null && dateTo == null && !priceFrom.isPresent() && !priceTo.isPresent()) {
                        Page<Orders> ordersPage = orderService.getOrdersWithUserId(checkAccountExisted.getUserId(), true, pageable);
                        if (!ordersPage.isEmpty() && ordersPage != null) {
                            return new ResponseEntity(ordersPage, HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        Page<Orders> ordersPage = orderService.getOrdersWithParameters(checkAccountExisted.getUserId(), dateFrom, dateTo,
                                priceFrom.orElse(null), priceTo.orElse(null), true, pageable);
                        if (!ordersPage.isEmpty() && ordersPage != null) {
                            return new ResponseEntity(ordersPage, HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                        }
                    }
                } else {
                    return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                if (dateFrom == null && dateTo == null && !priceFrom.isPresent() && !priceTo.isPresent()) {
                    Page<Orders> ordersPage = orderService.getOrdersWithUserId(username.orElse(null), true, pageable);
                    if (!ordersPage.isEmpty() && ordersPage != null) {
                        return new ResponseEntity(ordersPage, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Page<Orders> ordersPage = orderService.getOrdersWithParameters(username.orElse(null), dateFrom, dateTo,
                            priceFrom.orElse(null), priceTo.orElse(null), true, pageable);
                    if (!ordersPage.isEmpty() && ordersPage != null) {
                        return new ResponseEntity(ordersPage, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                }
            }
        } else if (AuthenticatedRole.isMySelf(username.orElse(null), authentication) && AuthenticatedRole.isUser(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
            if (dateFrom == null && dateTo == null && !priceFrom.isPresent() && !priceTo.isPresent()) {
                Page<Orders> ordersPage = orderService.getOrdersWithUserId(checkAccountExisted.getUserId(), false, pageable);
                if (!ordersPage.isEmpty() && ordersPage != null) {
                    return new ResponseEntity(ordersPage, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                Page<Orders> ordersPage = orderService.getOrdersWithParameters(checkAccountExisted.getUserId(), dateFrom, dateTo,
                        priceFrom.orElse(null), priceTo.orElse(null), false, pageable);
                if (!ordersPage.isEmpty() && ordersPage != null) {
                    return new ResponseEntity(ordersPage, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "create new order", responses = {
            @ApiResponse(
                    description = "create new order successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "create new order successfully!",
                                    value = "Create new order successfully!"
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
                    description = "create new order failed!",
                    responseCode = "400",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "create new order failed!",
                                    value = "Create new order failed!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/orders/carts/{cartId}")
    @Transactional
    public ResponseEntity createNewOrderWithCart(@RequestParam("username") Optional<String> username,
                                                 @RequestBody Account account,
                                                 @RequestParam("promotionId") Optional<String> promotionId,
                                                 @PathVariable String cartId,
                                                 Authentication authentication) {
        Promotion checkPromotionExisted = null;
        if (username.isPresent()) {
            if (AuthenticatedRole.isMySelf(username.orElse(null), authentication) && AuthenticatedRole.isUser(authentication)) {
                Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
                if (checkAccountExisted != null) {
                    Cart checkCartExisted = cartService.getCartByCartIdAndUserId(cartId, checkAccountExisted.getUserId(), 0);
                    if (checkCartExisted != null) {
                        List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartIdAndUserId(checkCartExisted.getCartId(), checkAccountExisted.getUserId());
                        if (!cartDetailList.isEmpty() && cartDetailList != null) {
                            //check product is not out of stock
                            boolean checkIsNotOutOfStock = productDetailService.checkProductIsNotOutOfStock(cartDetailList);
                            if (checkIsNotOutOfStock) {
                                if (promotionId.isPresent()) {
                                    checkPromotionExisted = promotionService.getPromotionByPromotionId(promotionId.orElse(null));
                                    if (checkPromotionExisted != null) {
                                        Orders orders = orderService.createNewOrders(account, checkAccountExisted, checkPromotionExisted, checkCartExisted);
                                        boolean check = orderDetailService.createOrderDetails(cartDetailList, orders);
                                        if (check) {
                                            checkCartExisted.setStatus(-1);
                                            cartService.deleteCart(checkCartExisted);
                                            return new ResponseEntity("Create new order successfully!", HttpStatus.OK);
                                        }
                                        return new ResponseEntity("Create new order failed!", HttpStatus.BAD_REQUEST);
                                    } else {
                                        return new ResponseEntity("Promotion is not available!", HttpStatus.NOT_FOUND);
                                    }
                                } else {
                                    Orders orders = orderService.createNewOrders(account, checkAccountExisted, null, checkCartExisted);
                                    boolean check = orderDetailService.createOrderDetails(cartDetailList, orders);
                                    if (check) {
                                        checkCartExisted.setStatus(-1);
                                        cartService.deleteCart(checkCartExisted);
                                        return new ResponseEntity("Create new order successfully!", HttpStatus.OK);
                                    }
                                    return new ResponseEntity("Create new order failed!", HttpStatus.BAD_REQUEST);
                                }
                            } else {
                                return new ResponseEntity("Product is out of stock!", HttpStatus.BAD_REQUEST);
                            }

                        } else {
                            return new ResponseEntity("Order detail is not found or empty!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity("Cart is not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity(("User does not found by user id!"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders/products/{productId}/{productSize}/{quantity}")
    @Transactional
    public ResponseEntity createNewOrderWithAProduct(@RequestParam("username") Optional<String> username,
                                                     @RequestBody Account account,
                                                     @RequestParam("promotionId") Optional<String> promotionId,
                                                     @PathVariable String productId,
                                                     @PathVariable String productSize,
                                                     @PathVariable int quantity,
                                                     Authentication authentication) {
        System.out.println(account);
        Promotion checkPromotionExisted = null;
        if (username.isPresent()) {
            if (AuthenticatedRole.isMySelf(username.orElse(null), authentication) && AuthenticatedRole.isUser(authentication)) {
                Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
                if (checkAccountExisted != null) {
                    Product checkProduct = productService.getProductByProductId(productId);
                    if (checkProduct != null) {

                    }
                    //check product is not out of stock
                    ProductDetail checkProductDetail = productDetailService.getProductDetailByProductIdAndProductSize(productId, productSize, 1);
                    if(checkProductDetail != null) {
                        if(checkProductDetail.getProQuantity() != 0) {
                            if (promotionId.isPresent()) {
                                checkPromotionExisted = promotionService.getPromotionByPromotionId(promotionId.orElse(null));
                                if (checkPromotionExisted != null) {
                                    Orders orders = orderService.createNewOrderByProduct(account, checkAccountExisted, checkPromotionExisted, checkProductDetail, checkProduct, quantity);
                                    boolean check = orderDetailService.createOrderDetailsByAProduct(quantity, checkProduct, checkProductDetail, orders);
                                    if (check) {
                                        return new ResponseEntity("Create new order successfully!", HttpStatus.OK);
                                    }
                                    return new ResponseEntity("Create new order failed!", HttpStatus.BAD_REQUEST);
                                } else {
                                    return new ResponseEntity("Promotion is not available!", HttpStatus.NOT_FOUND);
                                }
                            } else {
                                Orders orders = orderService.createNewOrderByProduct(account, checkAccountExisted, null, checkProductDetail, checkProduct, quantity);
                                boolean check = orderDetailService.createOrderDetailsByAProduct(quantity, checkProduct, checkProductDetail, orders);
                                if (check) {
                                    return new ResponseEntity("Create new order successfully!", HttpStatus.OK);
                                }
                                return new ResponseEntity("Create new order failed!", HttpStatus.BAD_REQUEST);
                            }
                        }else {
                            return new ResponseEntity("Product is out of stock!", HttpStatus.BAD_REQUEST);
                        }
                    }else {
                        return new ResponseEntity("Can not found by product id and product size!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Account is not available!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity("User does not found!", HttpStatus.NOT_FOUND);
        }
    }


    @Operation(description = "get order by username", responses = {
            @ApiResponse(
                    description = "get order successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Orders.class)
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
    @GetMapping("/users/{username}/orders/{orderId}")
    public ResponseEntity getOrderByOrderId(@PathVariable("username") String username,
                                            @PathVariable("orderId") String orderId,
                                            Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication) && !AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username);
            if (checkAccountExisted != null) {
                Orders checkOrderExisted = orderService.findOrderByOrderIdWithUserId(orderId, checkAccountExisted.getUserId());
                if (checkOrderExisted != null) {
                    return new ResponseEntity(checkOrderExisted, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Order is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else if (AuthenticatedRole.isAdmin(authentication)) {
            Orders checkOrderExisted = orderService.findOrderByOrderIdWithAdminAndSeller(orderId);
            if (checkOrderExisted != null) {
                return new ResponseEntity(checkOrderExisted, HttpStatus.OK);
            } else {
                return new ResponseEntity("Order is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "delete order", responses = {
            @ApiResponse(
                    description = "delete order successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "delete order successfully!",
                                    value = "delete order successfully!"
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
    @DeleteMapping("/users/{userName}/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") String orderId,
                                      @PathVariable("userName") String userName,
                                      Authentication authentication) {
        if (AuthenticatedRole.isMySelf(userName, authentication) && AuthenticatedRole.isSeller(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(userName);
            if (checkAccountExisted != null) {
                Orders checkOrderExisted = orderService.findOrderByOrderIdWithAdminAndSeller(orderId);
                if (checkOrderExisted != null) {
                    orderService.changeStatusOrders(checkOrderExisted, -1);
                    return new ResponseEntity("Delete order successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Order is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(description = "confirm order", responses = {
            @ApiResponse(
                    description = "confirm order successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "confirm order successfully!",
                                    value = "confirm order successfully!"
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
    @PutMapping("/users/{userName}/orders/{orderId}")
    public ResponseEntity confirmOrder(@PathVariable("orderId") String orderId,
                                       @PathVariable("userName") String userName,
                                       Authentication authentication) {
        if (AuthenticatedRole.isMySelf(userName, authentication) && AuthenticatedRole.isSeller(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(userName);
            if (checkAccountExisted != null) {
                Orders checkOrderExisted = orderService.findOrderByOrderIdWithAdminAndSeller(orderId);
                if (checkOrderExisted != null) {
                    if (checkOrderExisted.getStatus() != -1) {
                        checkOrderExisted.setSeller(checkAccountExisted);
                        checkOrderExisted.setSellerId(checkAccountExisted.getUserId());
                        orderService.changeStatusOrders(checkOrderExisted, 1);
                        return new ResponseEntity("Confirm order successfully!", HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Order have been deleted!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Order is not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Account is not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
