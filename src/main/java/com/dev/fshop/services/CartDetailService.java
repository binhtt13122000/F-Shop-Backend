package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.ProductDetail;

import java.util.List;

public interface CartDetailService {
    public List<CartDetail> getCartDetailsByCartId(String cartId);

    public CartDetail getCartDetailByCartIdAndProductIdAndCartSize(String cartId, String productId, String cartSize);

    public CartDetail addQuantityProductInCartDetailExisted(CartDetail cartDetail, Product product, Integer cartQuantity);

    public CartDetail addProductInCartDetail(Account account, Cart cart, Product product, ProductDetail productDetail, Integer cartQuantity);
}
