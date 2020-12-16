package com.dev.fshop.services;

import com.dev.fshop.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceInterface{
    @Autowired
    private ProductServiceInterface productServiceInterface;
    @Override
    public List<ProductEntity> getAllProducts() {
        return productServiceInterface.getAllProducts();
    }

    @Override
    public List<ProductEntity> findProductsByName(String productName) {
        return productServiceInterface.findProductsByName(productName);
    }

    @Override
    public ProductEntity findProductById(Integer proId) {
        return productServiceInterface.findProductById(proId);
    }

    @Override
    public List<ProductEntity> findProductByPrice(float priceFrom, float priceTo) {
        return productServiceInterface.findProductByPrice(priceFrom, priceTo);
    }

    @Override
    public List<ProductEntity> findProductByType(String type) {
        return productServiceInterface.findProductByType(type);
    }

    @Override
    public List<ProductEntity> getNewProduct(Date date) {
        return productServiceInterface.getNewProduct(date);
    }

    @Override
    public List<ProductEntity> findGoodProduct(Integer star) {
        return productServiceInterface.findGoodProduct(star);
    }

    @Override
    public ProductEntity createNewProduct(ProductEntity productEntity) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
        return productServiceInterface.createNewProduct(productEntity);
    }

    @Override
    public ProductEntity updateProductExisted(Integer proId, ProductEntity productEntity) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
        return productServiceInterface.updateProductExisted(proId, productEntity);
    }

    @Override
    public boolean deleteProductExisted(Integer proId) {
        ProductEntity checkExisted = productServiceInterface.findProductById(proId);
        if(checkExisted == null) {
            return false;
        }
        else {
            productServiceInterface.deleteProductExisted(proId);
            return true;
        }
    }
}
