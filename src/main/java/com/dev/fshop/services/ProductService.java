package com.dev.fshop.services;

import com.dev.fshop.entities.Product;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public interface ProductService {
    public Product getProductByProId(String proId);
    public List<Product> getProducts();
    public Product updateProduct(Product product);
    public boolean addQuantity(Product product, Integer quantity);
    public Product createNewProduct(Product product);
}
