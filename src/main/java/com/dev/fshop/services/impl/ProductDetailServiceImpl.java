package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Product;
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
    public ProductDetail addQuantity(ProductDetail productDetail, int quantity) {
        productDetail.setProQuantity(productDetail.getProQuantity() + quantity);
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail getProductDetailByProductIdAndProductSize(String productId, String productSize) {
        return productDetailRepository.findProductDetailByProduct_ProductIdAndAndProSize(productId, productSize);
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
}
