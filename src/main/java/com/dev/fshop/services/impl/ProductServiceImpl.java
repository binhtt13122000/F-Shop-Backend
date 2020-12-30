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
    public Product getProductByProId(String proId) {
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
        currentProduct.setProPrice(newProduct.getProPrice());
        currentProduct.setProDescription(newProduct.getProDescription());
        currentProduct.setProName(newProduct.getProName());
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

}
