package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public interface ProductService {
    public Product getProductByProductId(String proId);
    public List<Product> getProducts(boolean isAdmin);
    public Product updateProduct(Product currentProduct, Product newProduct);
    public Product createNewProduct(Product product);
    public List<Product> searchProductsByParameterQ(boolean isAdmin, String q);
    public List<Product> searchProductsByParameters(boolean isAdmin, String productName, String categoryName, Float realPriceFrom, Float realPriceTo,
                                                    Date dateFrom, Date dateTo);
}
