package com.dev.fshop.services.impl;

import com.dev.fshop.repositories.CartDetailRepository;
import com.dev.fshop.services.CartDetailService;
import com.dev.fshop.supporters.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> getCartDetailsByCartId(String cartId) {
        return cartDetailRepository.getCartDetailsByCart_CartId(cartId);
    }
}
