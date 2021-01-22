package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.repositories.CartRepository;
import com.dev.fshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartByCartIdAndUserId(String cartId, String userId, int status) {
        return cartRepository.getCartByCartIdAndUserId(cartId, userId, status);
    }

    @Override
    public Cart createNewCart(Account accountExisted, Cart cart) {
        cart.setUserId(accountExisted.getUserId());
        cart.setAccount(accountExisted);
        cart.setCartTotal(0);
        cart.setCreateTime(new Date());
        cart.setStatus(1);
        return cartRepository.save(cart);
    }

    @Override
    public Page<Cart> getCartsByParameterQ(String userId, String q, Pageable pageable) {
        if (q != null) {
            q = "%" + q + "%";
        }
        return cartRepository.getCartsByParameterQByUser(1, q, userId, pageable);
    }

    @Override
    public Page<Cart> getCartByParameters(String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo,
                                          Pageable pageable) {
        return cartRepository.getCartsByParametersByUser(1, userId, dateFrom, dateTo, priceFrom, priceTo, pageable);
    }

    @Override
    public Cart updateCartTotal(Cart cart, float cartPrice) {
        cart.setCartTotal(cart.getCartTotal() + cartPrice);
        return cartRepository.save(cart);
    }

    @Override
    public Page<Cart> getAllCarts(String userId, Pageable pageable) {
        return cartRepository.getCartsByUserIdByUser(userId, 1, pageable);
    }

}
