package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CartService {

    public Cart getCartByCartIdAndUserId(String cartId, String userId, int status);

    public Cart createNewCart(Account accountExisted, Cart cart);

    public Page<Cart> getCartsByParameterQ(String userId, String q, Pageable pageable);

    public Page<Cart> getCartByParameters(String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo,
                                          Pageable pageable);

    public Cart updateCartTotal(Cart cart, float cartPrice);

    public Page<Cart> getAllCarts(String userId, Pageable pageable);

    public boolean deleteCart(Cart cart);

    public boolean changeStatusCart(Cart cart, int status);
}
