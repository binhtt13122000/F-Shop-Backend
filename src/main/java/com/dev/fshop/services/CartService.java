package com.dev.fshop.services;

import com.dev.fshop.entities.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getCartsByUserId(String userId);
    public Cart getCartByCartId(String cartId);
}
