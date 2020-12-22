package com.dev.fshop.services;

import com.dev.fshop.entities.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {
    //Get Methods
    public List<Product> getAllProducts();
    public List<Product> findProductsByName(String productName);
    public Product findProductById(String proId);
    public List<Product> findProductByPrice(float priceFrom, float priceTo);
    public List<Product> findProductByType(String type);
    public List<Product> getNewProduct(Date date);
    public List<Product> findGoodProduct(Integer star);



    //Post Methods
    public Product createNewProduct(Product product);


    //Put Methods
    public Product updateProductExisted(String proId, Product product);


    //Delete Methods
    public boolean deleteProductExisted(String proId);
}
