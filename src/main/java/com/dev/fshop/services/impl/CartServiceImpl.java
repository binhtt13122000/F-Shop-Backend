package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Cart;
import com.dev.fshop.repositories.CartRepository;
import com.dev.fshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<Cart> getCartsByUserId(String userId) {
        return cartRepository.getCartsByAccount_UserId(userId);
    }

    @Override
    public Cart getCartByCartId(String cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
}
