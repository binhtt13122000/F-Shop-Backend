package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.ProductRepository;
import com.dev.fshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductByProductId(String proId) {
        return productRepository.findById(proId).orElse(null);
    }

    @Override
    public List<Product> getProducts(boolean isAdmin) {
        if (isAdmin) {
            System.out.println(productRepository.findAll());
            return productRepository.findAll();
        }else {
            return productRepository.getProductsByStatus(1);
        }
    }

    @Override
    public Product updateProduct(Product currentProduct, Product newProduct) {
        System.out.println(newProduct.getCategory().getProTypeId());
        currentProduct.setProductPrice(newProduct.getProductPrice());
        currentProduct.setProductDescription(newProduct.getProductDescription());
        currentProduct.setProductName(newProduct.getProductName());
        currentProduct.setRealPrice(newProduct.getRealPrice());
        currentProduct.setUpdateAt(new Date());
        currentProduct.setCategory(newProduct.getCategory());
        currentProduct.setSupplier(newProduct.getSupplier());
        return productRepository.save(currentProduct);
    }


    @Override
    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> searchProductsByParameterQ(boolean isAdmin, String q) {
        if(isAdmin) {
            return productRepository.searchProductsByParameterQWithAdmin(q);
        }
        else {
            return productRepository.searchProductsByParameterQWithUser(1, q);
        }
    }

    @Override
    public List<Product> searchProductsByParameters(boolean isAdmin, String productName, String categoryName, Float realPriceFrom, Float realPriceTo, Date dateFrom, Date dateTo) {
        if(productName != null) {
            productName = "%" + productName + "%";
        }
        if(categoryName != null) {
            categoryName = "%" + categoryName + "%";
        }
        if(isAdmin) {
            return productRepository.searchProductsByParametersWithAdmin(productName, categoryName, realPriceFrom, realPriceTo, dateFrom, dateTo);
        }else {
            return productRepository.searchProductsByParametersWithUser(1, productName, categoryName, realPriceFrom, realPriceTo, dateFrom, dateTo);
        }
    }
}