package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

public interface ProductService {
    public Product getProductByProductId(String proId);

    public Page<Product> getProducts(boolean isAdmin, Pageable pageable);

    public Product updateProduct(Product currentProduct, Product newProduct);

    public Product createNewProduct(Product product);

    public Page<Product> searchProductsByParameterQ(boolean isAdmin, String q, Pageable pageable);

    public Page<Product> searchProductsByParameters(boolean isAdmin, String productName, String categoryName, Float realPriceFrom, Float realPriceTo,
                                                    Date dateFrom, Date dateTo, Pageable pageable);

    public boolean changeStatusProductByProductId(Product currentProduct);
}
