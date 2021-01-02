package com.dev.fshop.controllers;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.services.AccountService;
import com.dev.fshop.services.CartDetailService;
import com.dev.fshop.services.CartService;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.utils.AuthenticatedRole;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private AccountService accountService;

    @Operation(description = "get carts", responses = {
            @ApiResponse(
                    description = "get carts successfully!",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Cart.class))
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
                    description = "Account is not available! or Cart is not found!",
                    responseCode = "404",
                    content = @Content(
                            mediaType = "text/plain; charset=utf-8",
                            examples = @ExampleObject(
                                    description = "Account is not available! or Cart is not found!",
                                    value = "Account is not available! or Cart is not found!"
                            ),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/carts/users/{username}")
    public ResponseEntity getCartsByUsername(@PathVariable String username, Authentication authentication) {
        if (AuthenticatedRole.isMySelf(username, authentication) || AuthenticatedRole.isAdmin(authentication)) {
            Account checkExistedAccount = accountService.getUserByUsername(username);
            if (checkExistedAccount != null) {
                List<Cart> cartList = cartService.getCartsByUserId(checkExistedAccount.getUserId());
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
                            array = @ArraySchema(schema = @Schema(implementation = CartDetail.class))
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
                            examples = @ExampleObject(description = "Not found!", value = "Not found!"),
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/carts/{cartId}/users/{username}/cartDetails")
    public ResponseEntity getCartDetailsByCartId(@PathVariable String cartId, @PathVariable String username, Authentication authentication) {
        if(AuthenticatedRole.isMySelf(username, authentication) || AuthenticatedRole.isAdmin(authentication)) {
            Cart checkCartExisted = cartService.getCartByCartId(cartId);
            if(checkCartExisted != null) {
                List<CartDetail> cartDetailList = cartDetailService.getCartDetailsByCartId(cartId);
                if(!cartDetailList.isEmpty() && cartDetailList != null) {
                    return new ResponseEntity(cartDetailList, HttpStatus.OK);
                }else {
                    return new ResponseEntity("Not found!", HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity("Cart is not available!", HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        }
    }
}
