package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.ProductDetail;


public interface ProductDetailService {
    public ProductDetail addQuantity(ProductDetail product);
    public ProductDetail getProductDetailByProIdAndProSize(String proId, String proSize);
}
