package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.ProductDetailRepository;
import com.dev.fshop.services.ProductDetailService;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetail addQuantity(ProductDetail productDetail, int quantity) {
        productDetail.setProQuantity(productDetail.getProQuantity() + quantity);
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail getProductDetailByProductIdAndProductSize(String productId, String productSize) {
        return productDetailRepository.findProductDetailByProductIdAndAndProSize(productId, productSize);
    }

    @Override
    public ProductDetail createNewProductDetail(Product product, String proSize, int quantity) {
        ProductDetail newProductDetail = new ProductDetail();
        newProductDetail.setProduct(product);
        newProductDetail.setProSize(proSize);
        newProductDetail.setProQuantity(quantity);
        newProductDetail.setStatus(1);
        return productDetailRepository.save(newProductDetail);
    }

    @Override
    public boolean checkProductIsNotOutOfStock(List<CartDetail> cartDetailList) {
        for (CartDetail cartDetail : cartDetailList
        ) {
            ProductDetail productDetail = productDetailRepository.findProductDetailByProductIdAndAndProSize(cartDetail.getProduct().getProductId(),
                    cartDetail.getCartSize());
            if (productDetail == null) {
                return false;
            } else {
                if (cartDetail.getCartQuantity() > productDetail.getProQuantity()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ProductDetail updateQuantityProductDetail(ProductDetail productDetail, int quantity) {
        productDetail.setProQuantity(productDetail.getProQuantity() - quantity);
        return productDetailRepository.save(productDetail);
    }
}
