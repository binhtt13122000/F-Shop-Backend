package com.dev.fshop.controllers;

import com.dev.fshop.entities.*;
import com.dev.fshop.services.*;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.OrderDetail;
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
            @RequestParam(required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date dateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date dateTo,
            @RequestParam Optional<Integer> status,
            @RequestParam Optional<Integer> pageIndex,
            @RequestParam Optional<Integer> pageSize,
            Authentication authentication
    ) {
        Pageable pageable = PageRequest.of(pageIndex.orElse(1) - 1, pageSize.orElse(4));
        if (AuthenticatedRole.isAdmin(authentication)) {
            if (username.isPresent()) {
                Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
                if (checkAccountExisted != null) {
                    if (dateFrom == null && dateTo == null) {
                        Page<Orders> ordersPage = orderService.getOrdersWithUserId(checkAccountExisted.getUserId(), true, status.orElse(2), pageable);
                        if (!ordersPage.isEmpty() && ordersPage != null) {
                            return new ResponseEntity(ordersPage, HttpStatus.OK);
                        } else {
                            return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        Page<Orders> ordersPage = orderService.getOrdersWithParameters(checkAccountExisted.getUserId(), dateFrom, dateTo, status.orElse(2),
                                 true, pageable);
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
                if (dateFrom == null && dateTo == null) {
                    Page<Orders> ordersPage = orderService.getOrdersWithUserId(username.orElse(null), true, status.orElse(2), pageable);
                    if (!ordersPage.isEmpty() && ordersPage != null) {
                        return new ResponseEntity(ordersPage, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Page<Orders> ordersPage = orderService.getOrdersWithParameters(username.orElse(null), dateFrom, dateTo, status.orElse(2),
                             true, pageable);
                    if (!ordersPage.isEmpty() && ordersPage != null) {
                        return new ResponseEntity(ordersPage, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                    }
                }
            }
        } else if (AuthenticatedRole.isMySelf(username.orElse(null), authentication) && AuthenticatedRole.isUser(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
            if (dateFrom == null && dateTo == null ) {
                System.out.println(status);
                Page<Orders> ordersPage = orderService.getOrdersWithUserId(checkAccountExisted.getUserId(), false, status.orElse(2), pageable);
                if (!ordersPage.isEmpty() && ordersPage != null) {
                    return new ResponseEntity(ordersPage, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            } else {
                Page<Orders> ordersPage = orderService.getOrdersWithParameters(checkAccountExisted.getUserId(), dateFrom, dateTo, status.orElse(2),
                         false, pageable);
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

    @Operation(description = "get order details", responses = {
            @ApiResponse(
                    description = "get order details successfully!",
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
    @GetMapping("/orders/{orderId}/orderDetails")
    public ResponseEntity getOrderDetailsByOrderId(
            @RequestParam Optional<String> userName,
            @PathVariable String orderId,
            Authentication authentication
    ) {
        if (AuthenticatedRole.isAdmin(authentication)) {
            Orders checkOrder = orderService.findOrderByOrderIdWithAdminAndSeller(orderId);
            if (checkOrder != null) {
                List<OrderDetail> orderDetailList = orderDetailService.getListOrderDetailsByOrder(checkOrder);
                if (orderDetailList.size() != 0 && !orderDetailList.isEmpty()) {
                    return new ResponseEntity(orderDetailList, HttpStatus.OK);
                } else {
                    return new ResponseEntity("Not found order detail by order!", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Not found order by order id", HttpStatus.NOT_FOUND);
            }
        } else if (AuthenticatedRole.isMySelf(userName.orElse(null), authentication)) {
            Account checkUser = accountService.getUserByUsername(userName.orElse(null));
            if (checkUser != null) {
                Orders checkOrder = orderService.findOrderByOrderIdWithUserId(orderId, checkUser.getUserId());
                if (checkOrder != null) {
                    List<OrderDetail> orderDetailList = orderDetailService.getListOrderDetailsByOrder(checkOrder);
                    if (orderDetailList.size() != 0 && !orderDetailList.isEmpty()) {
                        return new ResponseEntity(orderDetailList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity("Not found order detail by order!", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("Not found order by order id", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("Not found user by username!", HttpStatus.NOT_FOUND);
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
                                if (promotionId.isPresent() && !promotionId.get().equals("")) {
                                    checkPromotionExisted = promotionService.getPromotionByPromotionId(promotionId.orElse(null));
                                    if (checkPromotionExisted != null) {
                                        Orders orders = orderService.createNewOrders(account, checkAccountExisted, checkPromotionExisted, checkCartExisted);
                                        boolean check = orderDetailService.createOrderDetails(cartDetailList, orders);
                                        if (check) {
                                            checkCartExisted.setStatus(-1);
                                            cartService.deleteCart(checkCartExisted);
                                            promotionService.changeStatusPromotion(checkPromotionExisted, false);
                                            createVoucherInOrder(checkCartExisted, checkAccountExisted, checkPromotionExisted);
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
                                        createVoucherInOrder(checkCartExisted, checkAccountExisted, checkPromotionExisted);
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

    private void createVoucherInOrder(Cart checkCartExisted, Account checkAccountExisted, Promotion checkPromotionExisted) {
        float promoion = 0;
        float total = 0;
        if(checkPromotionExisted == null) {
            total = checkCartExisted.getCartTotal();
        }else {
            total = checkCartExisted.getCartTotal() - (checkCartExisted.getCartTotal() * checkPromotionExisted.getPromo() / 100);
        }
        System.out.println(total);
        if (total >= 1000000 && total < 2000000) {
            promoion = 10;
        } else if (total >= 2000000 && total < 5000000) {
            promoion = 20;
        } else if (total >= 5000000 && total < 8000000) {
            promoion = 30;
        } else if (total >= 8000000 && total < 10000000) {
            promoion = 40;
        } else if (total >= 10000000 && total < 14000000) {
            promoion = 50;
        } else if (total >= 14000000) {
            promoion = 60;
        }
        Promotion createPromotion = new Promotion();
        createPromotion.setPromotionName("GIẢM GIÁ " + promoion + "%");
        createPromotion.setPromo(promoion);
        promotionService.createPromotion(createPromotion, checkAccountExisted);
    }

    private void createVoucherWithProduct(Product product, int quantity, Account checkAccountExisted, Promotion checkPromotionExisted) {
        float promotion = 0;
        float total = 0;
        if(checkPromotionExisted == null) {
            total = quantity * product.getProductPrice();
        }else {
            total = quantity * product.getProductPrice() - (quantity * product.getProductPrice() * checkPromotionExisted.getPromo() / 100);
        }
        if (total >= 1000000 && total < 2000000) {
            promotion = 10;
        } else if (total >= 2000000 && total < 5000000) {
            promotion = 20;
        } else if (total >= 5000000 && total < 8000000) {
            promotion = 30;
        } else if (total >= 8000000 && total < 10000000) {
            promotion = 40;
        } else if (total >= 10000000 && total < 14000000) {
            promotion = 50;
        } else if (total >= 14000000) {
            promotion = 60;
        }
        if(promotion != 0.0) {
            Promotion createPromotion = new Promotion();
            createPromotion.setPromotionName("GIẢM GIÁ " + promotion + "%");
            createPromotion.setPromo(promotion);
            promotionService.createPromotion(createPromotion, checkAccountExisted);
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
        Promotion checkPromotionExisted = null;
        if (username.isPresent()) {
            if (AuthenticatedRole.isMySelf(username.orElse(null), authentication) && AuthenticatedRole.isUser(authentication)) {
                Account checkAccountExisted = accountService.getUserByUsername(username.orElse(null));
                if (checkAccountExisted != null) {
                    Product checkProduct = productService.getProductByProductId(productId);
                    if (checkProduct != null) {
                        ProductDetail checkProductDetail = productDetailService.getProductDetailByProductIdAndProductSize(productId, productSize, 1);
                        if (checkProductDetail != null) {
                            if (checkProductDetail.getProQuantity() != 0) {
                                if(checkProductDetail.getProQuantity() >= quantity) {
                                    if (promotionId.isPresent() && !promotionId.get().equals("")) {
                                        checkPromotionExisted = promotionService.getPromotionByPromotionId(promotionId.orElse(null));
                                        if (checkPromotionExisted != null) {
                                            Orders orders = orderService.createNewOrderByProduct(account, checkAccountExisted, checkPromotionExisted, checkProductDetail, checkProduct, quantity);
                                            boolean check = orderDetailService.createOrderDetailsByAProduct(quantity, checkProduct, checkProductDetail, orders);
                                            if (check) {
                                                promotionService.changeStatusPromotion(checkPromotionExisted, false);
                                                createVoucherWithProduct(checkProduct, quantity,checkAccountExisted,checkPromotionExisted);
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
                                            createVoucherWithProduct(checkProduct, quantity,checkAccountExisted,checkPromotionExisted);
                                            return new ResponseEntity("Create new order successfully!", HttpStatus.OK);
                                        }
                                        return new ResponseEntity("Create new order failed!", HttpStatus.BAD_REQUEST);
                                    }
                                }else {
                                    return new ResponseEntity("Quantity of product details is not enough to order!", HttpStatus.BAD_REQUEST);
                                }
                            } else {
                                return new ResponseEntity("Product is out of stock!", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return new ResponseEntity("Can not found by product id and product size!", HttpStatus.NOT_FOUND);
                        }
                    }else {
                        return new ResponseEntity("Product can not found by product id!", HttpStatus.NOT_FOUND);
                    }
                    //check product is not out of stock
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
        if (AuthenticatedRole.isMySelf(userName, authentication) || AuthenticatedRole.isAdmin(authentication)) {
            Account checkAccountExisted = accountService.getUserByUsername(userName);
            if (checkAccountExisted != null) {
                Orders checkOrderExisted = orderService.findOrderByOrderIdWithAdminAndSeller(orderId);
                if (checkOrderExisted != null) {
                    if(AuthenticatedRole.isAdmin(authentication)) {
                        orderService.changeStatusOrders(checkOrderExisted, -1);
                        return new ResponseEntity("Delete order successfully!", HttpStatus.OK);
                    }else {
                        if(checkOrderExisted.getUserId().equals(checkAccountExisted.getUserId())) {
                            orderService.changeStatusOrders(checkOrderExisted, -1);
                            return new ResponseEntity("Delete order successfully!", HttpStatus.OK);
                        }else {
                            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
                        }
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
        if (AuthenticatedRole.isAdmin(authentication) || AuthenticatedRole.isSeller(authentication)) {
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
