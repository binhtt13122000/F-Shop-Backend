package com.dev.fshop.services.impl;

import com.dev.fshop.repositories.ProductDetailRepository;
import com.dev.fshop.services.ProductDetailService;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetail addQuantity(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail getProductDetailByProIdAndProSize(String proId, String proSize) {
        return productDetailRepository.findProductDetailByProductProIdAndProSize(proId, proSize);
    }
}
