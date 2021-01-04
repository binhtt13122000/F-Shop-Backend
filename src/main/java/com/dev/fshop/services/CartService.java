package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;

import java.util.Date;
import java.util.List;

public interface CartService {

    public Cart getCartByCartId(String cartId);

    public Cart createNewCart(Account accountExisted, Cart cart);

    public List<Cart> getCartsByParameterQ(boolean isAdmin, String userId, String q);

    public List<Cart> getCartByParameters(boolean isAdmin, String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo);

    public Cart updateCartTotal(Cart cart, float cartPrice);

    public List<Cart> getAllCarts(boolean isAdmin, String userId);

}
