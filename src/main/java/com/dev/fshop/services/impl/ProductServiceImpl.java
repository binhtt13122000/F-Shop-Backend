package com.dev.fshop.services.impl;

import com.dev.fshop.entity.ProductEntity;
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
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findProductsByName(String productName) {
//        return productRepository.searchProductByName(productName);
        return null;
    }

    @Override
    public ProductEntity findProductById(String proId) {
        return productRepository.findById(proId).orElse(null);
    }

    @Override
    public List<ProductEntity> findProductByPrice(float priceFrom, float priceTo) {
//        return productRepository.searchProductByPrice(priceFrom,priceTo);
        return null;
    }


    @Override
    public List<ProductEntity> findProductByType(String type) {
        return null;
//        return productRepository.searchProductByType(type);
    }

    @Override
    public List<ProductEntity> getNewProduct(Date date) {
        return null;
//        return productRepository.findNewProduct(date);
    }

    @Override
    public List<ProductEntity> findGoodProduct(Integer star) {
        return null;
//        return productRepository.findGoodProduct(star);
    }

    @Override
    public ProductEntity createNewProduct(ProductEntity productEntity) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
//        return productRepository.insertProductWithEntityManager(productEntity);
        return null;
    }

    @Override
    public ProductEntity updateProductExisted(String proId, ProductEntity productEntity) {
//        ProductEntity checkExisted = productServiceInterface.findProductById(productEntity.getProId());
//        if(checkExisted == null) {
//
//        }
//        return productRepository.updateProduct(productEntity,proId);
        return null;
    }

    @Override
    public boolean deleteProductExisted(String proId) {
        ProductEntity checkExisted = productRepository.findById(proId).orElse(null);
        if(checkExisted == null) {
            return false;
        }
        else {
//            productRepository.deleteProductInOrder(proId);
            return true;
        }
    }
}
