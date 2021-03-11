package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductDetailService {
    public ProductDetail addQuantity(ProductDetail product, int quantity);

    public ProductDetail getProductDetailByProductIdAndProductSize(String productId, String productSize);

    public ProductDetail createNewProductDetail(Product product, String proSize, int quantity);

    public boolean checkProductIsNotOutOfStock(List<CartDetail> cartDetailList);

    public ProductDetail updateQuantityProductDetail(ProductDetail productDetail, int quantity);

    public boolean checkProductDetailIsNotOutOfStockByProductId(String productId, int status, int quantity);
}
