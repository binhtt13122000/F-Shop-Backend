package com.dev.fshop.controller;

import com.dev.fshop.entity.ProductEntity;
import com.dev.fshop.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class ProductController {
    @Autowired
    private ProductServiceInterface productServiceInterface;

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok().body(productServiceInterface.getAllProducts());
    }

    @GetMapping(path = "/products/{proName}")
    public ResponseEntity<List<ProductEntity>> findProductByName(@PathVariable String proName) {
        return ResponseEntity.ok().body(productServiceInterface.findProductsByName(proName));
    }

    @GetMapping(path = "/products/{proId}")
    public ResponseEntity<ProductEntity> findProductById(@PathVariable Integer proId) {
        return ResponseEntity.ok().body(productServiceInterface.findProductById(proId));
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductEntity>> findProductByPrice(@RequestParam(name = "priceFrom") float priceFrom, @RequestParam(name = "priceTo") float priceTo) {
        return ResponseEntity.ok().body(productServiceInterface.findProductByPrice(priceFrom, priceTo));
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductEntity>> findProductByType(@RequestParam(name = "type") String type) {
        return ResponseEntity.ok().body(productServiceInterface.findProductByType(type));
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductEntity>> getNewProduct(@RequestParam(name = "createAt")Date createAt) {
        return ResponseEntity.ok().body(productServiceInterface.getNewProduct(createAt));
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductEntity>> findGoodProducts(@RequestParam(name = "star")Integer star) {
        return ResponseEntity.ok().body(productServiceInterface.findGoodProduct(star));
    }

    @PostMapping(path = "/products")
    public ResponseEntity<ProductEntity> createNewProduct(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok().body(productServiceInterface.createNewProduct(productEntity));
    }

    @PutMapping(path = "/products/{proId}")
    public ResponseEntity<ProductEntity> updateProductExisted(@PathVariable Integer proId, @RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok().body(productServiceInterface.updateProductExisted(proId, productEntity));
    }

    @DeleteMapping(path = "/products/{proId}")
    public boolean deleteProductExisted(@PathVariable Integer proId) {
        productServiceInterface.deleteProductExisted(proId);
        return true;
    }

}
