package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.ProductDetail;


public interface ProductDetailService {
    public ProductDetail addQuantity(ProductDetail product, int quantity);
    public ProductDetail getProductDetailByProIdAndProSize(String proId, String proSize);
    public ProductDetail createNewProductDetail(Product product, String proSize, int quantity);
}
