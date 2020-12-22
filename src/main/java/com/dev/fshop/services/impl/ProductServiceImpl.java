package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.ProductRepository;
import com.dev.fshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByName(String productName) {
//        return productRepository.findProductEntitiesByProName(productName);
        return null;

    }

    @Override
    public Product findProductById(String proId) {
        return productRepository.findById(proId).orElse(null);
    }

    @Override
    public List<Product> findProductByPrice(float priceFrom, float priceTo) {
//        return productRepository.findProductEntitiesByProPrice(priceFrom,priceTo);
        return null;

    }

    @Override
    public List<Product> findProductByType(String type) {
//        return productRepository.findProductEntitiesByCategoryEntity(type);
        return null;

    }

    @Override
    public List<Product> getNewProduct(Date date) {
        return null;

//        return productRepository.findNewProduct(date);
    }

    @Override
    public List<Product> findGoodProduct(Integer star) {
        return null;

//        return productRepository.findGoodProduct(star);
    }

    @Override
    public Product createNewProduct(Product product) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProductExisted(String proId, Product product) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProductExisted(String proId) {
        Product checkExisted = productRepository.findById(proId).orElse(null);
        if(checkExisted == null) {
            return false;
        }
        else {
            productRepository.deleteById(proId);
            return true;
        }
    }
}
