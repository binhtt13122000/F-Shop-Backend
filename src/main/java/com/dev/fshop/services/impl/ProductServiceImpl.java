package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Product;
import com.dev.fshop.repositories.ProductRepository;
import com.dev.fshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.data.domain.Pageable;

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
    public Page<Product> getProducts(boolean isAdmin, Pageable pageable) {
        if (isAdmin) {
            System.out.println(productRepository.findAll());
            return productRepository.findAll(pageable);
        } else {
            return productRepository.getProductsByStatus(1, pageable);
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
    public Page<Product> searchProductsByParameterQ(boolean isAdmin, String q, Pageable pageable) {
        if (isAdmin) {
            return productRepository.searchProductsByParameterQWithAdmin(q, pageable);
        } else {
            return productRepository.searchProductsByParameterQWithUser(1, q, pageable);
        }
    }

    @Override
    public Page<Product> searchProductsByParameters(boolean isAdmin, String productName, String categoryName, Float realPriceFrom,
                                                    Float realPriceTo, Date dateFrom, Date dateTo, Pageable pageable) {
        if (productName != null) {
            productName = "%" + productName + "%";
        }
        if (categoryName != null) {
            categoryName = "%" + categoryName + "%";
        }
        if (isAdmin) {
            return productRepository.searchProductsByParametersWithAdmin(productName, categoryName, realPriceFrom, realPriceTo, dateFrom, dateTo, pageable);
        } else {
            return productRepository.searchProductsByParametersWithUser(1, productName, categoryName, realPriceFrom, realPriceTo, dateFrom, dateTo, pageable);
        }
    }

    @Override
    public boolean changeStatusProductByProductId(Product currentProduct, int status) {
        currentProduct.setStatus(status);
        productRepository.save(currentProduct);
        return true;
    }

}
