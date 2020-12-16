package com.dev.fshop.controller;

import com.dev.fshop.entity.ProductEntity;
import com.dev.fshop.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class ProductController {
    @Autowired
    private ProductServiceInterface productServiceInterface;

    @GetMapping(path = "/products")
    public List<ProductEntity> getAllProducts() {
        return productServiceInterface.getAllProducts();
    }

    @GetMapping(path = "/products/{proName}")
    public List<ProductEntity> findProductByName(@PathVariable String proName) {
        return productServiceInterface.findProductsByName(proName);
    }

    @GetMapping(path = "/products/{proId}")
    public ProductEntity findProductById(@PathVariable Integer proId) {
        return productServiceInterface.findProductById(proId);
    }

    @GetMapping(path = "/products")
    public List<ProductEntity> findProductByPrice(@RequestParam(name = "priceFrom") float priceFrom, @RequestParam(name = "priceTo") float priceTo) {
        return productServiceInterface.findProductByPrice(priceFrom, priceTo);
    }

    @GetMapping(path = "/products")
    public List<ProductEntity> findProductByType(@RequestParam(name = "type") String type) {
        return productServiceInterface.findProductByType(type);
    }

    @GetMapping(path = "/products")
    public List<ProductEntity> getNewProduct(@RequestParam(name = "createAt")Date createAt) {
        return productServiceInterface.getNewProduct(createAt);
    }

    @GetMapping(path = "/products")
    public List<ProductEntity> findGoodProducts(@RequestParam(name = "star")Integer star) {
        return productServiceInterface.findGoodProduct(star);
    }

    @PostMapping(path = "/products")
    public ProductEntity createNewProduct(@RequestBody ProductEntity productEntity) {
        return productServiceInterface.createNewProduct(productEntity);
    }

    @PutMapping(path = "/products/{proId}")
    public ProductEntity updateProductExisted(@PathVariable Integer proId, @RequestBody ProductEntity productEntity) {
        return productServiceInterface.updateProductExisted(proId, productEntity);
    }

    @DeleteMapping(path = "/products/{proId}")
    public boolean deleteProductExisted(@PathVariable Integer proId) {
        productServiceInterface.deleteProductExisted(proId);
        return true;
    }

}
