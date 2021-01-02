package com.dev.fshop.services;

import com.dev.fshop.supporters.CartDetail;

import java.util.List;

public interface CartDetailService {
    public List<CartDetail> getCartDetailsByCartId(String cartId);
}
