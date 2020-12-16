package com.dev.fshop.services;

import com.dev.fshop.entity.ProductEntity;

import java.util.Date;
import java.util.List;

public interface ProductServiceInterface {
    //Get Methods
    public List<ProductEntity> getAllProducts();
    public List<ProductEntity> findProductsByName(String productName);
    public ProductEntity findProductById(Integer proId);
    public List<ProductEntity> findProductByPrice(float priceFrom, float priceTo);
    public List<ProductEntity> findProductByType(String type);
    public List<ProductEntity> getNewProduct(Date date);
    public List<ProductEntity> findGoodProduct(Integer star);



    //Post Methods
    public ProductEntity createNewProduct(ProductEntity productEntity);


    //Put Methods
    public ProductEntity updateProductExisted(Integer proId, ProductEntity productEntity);


    //Delete Methods
    public boolean deleteProductExisted(Integer proId);
}
