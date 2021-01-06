package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CartService {

    public Cart getCartByCartId(String cartId);

    public Cart createNewCart(Account accountExisted, Cart cart);

    public Page<Cart> getCartsByParameterQ(boolean isAdmin, String userId, String q, Pageable pageable);

    public Page<Cart> getCartByParameters(boolean isAdmin, String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo,
                                          Pageable pageable);

    public Cart updateCartTotal(Cart cart, float cartPrice);

    public Page<Cart> getAllCarts(boolean isAdmin, String userId, Pageable pageable);

}
