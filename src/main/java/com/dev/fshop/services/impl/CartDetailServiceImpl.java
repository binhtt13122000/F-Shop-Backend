package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Cart;
import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.CartDetailRepository;
import com.dev.fshop.services.CartDetailService;
import com.dev.fshop.services.CartService;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private CartService cartService;

    @Override
    public List<CartDetail> getCartDetailsByCartId(String cartId) {
        return cartDetailRepository.getCartDetailsByCart_CartId(cartId);
    }

    @Override
    public CartDetail getCartDetailByCartIdAndProductIdAndCartSize(String cartId, String productId, String cartSize) {
        return cartDetailRepository.getCartDetailByCartIdAndProductIdAndCartSize(cartId, productId, cartSize);
    }

    @Override
    public CartDetail addQuantityProductInCartDetailExisted(CartDetail cartDetail, Product product, Integer cartQuantity) {
        float price = product.getProductPrice() * cartQuantity;
        cartDetail.setCartQuantity(cartDetail.getCartQuantity() + cartQuantity);
        cartDetail.setCartItemPrice(cartDetail.getCartItemPrice() + price);
        Cart updateCart = cartService.updateCartTotal(cartDetail.getCart(), price);
        if(updateCart != null) {
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }

    @Override
    public CartDetail addProductInCartDetail(Account account, Cart cart, Product product, ProductDetail productDetail, Integer cartQuantity) {
        float price = product.getProductPrice() * cartQuantity;
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setCartId(cart.getCartId());
        cartDetail.setProduct(product);
        cartDetail.setProductId(product.getProductId());
        cartDetail.setCartSize(productDetail.getProSize());
        cartDetail.setCartQuantity(cartQuantity);
        cartDetail.setCartItemPrice(price);
        Cart updateCart = cartService.updateCartTotal(cart, price);
        if(updateCart != null) {
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }
}
