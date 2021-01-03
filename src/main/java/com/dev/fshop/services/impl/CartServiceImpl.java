package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.repositories.CartRepository;
import com.dev.fshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Cart> getCartsByParameterQ(boolean isAdmin, String userId, String q) {
        if (q != null) {
            q = "%" + q + "%";
        }
        if (isAdmin) {
            return cartRepository.getCartsByParameterQByAdmin(q, userId);
        }
        return cartRepository.getCartsByParameterQByUser(1, q, userId);
    }

    @Override
    public List<Cart> getCartByParameters(boolean isAdmin, String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo) {
        if (isAdmin) {
            return cartRepository.getCartsByParametersByAdmin(userId, dateFrom, dateTo, priceFrom, priceTo);
        }
        return cartRepository.getCartsByParametersByUser(1, userId, dateFrom, dateTo, priceFrom, priceTo);
    }

    @Override
    public Cart updateCartTotal(Cart cart, float cartPrice) {
        cart.setCartTotal(cart.getCartTotal() + cartPrice);
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts(boolean isAdmin, String userId) {
        if (isAdmin) {
            return cartRepository.getCartsByAccount_UserId(userId);
        } else {
            return cartRepository.getCartsByUserIdByUser(userId, 1);
        }
    }

}
