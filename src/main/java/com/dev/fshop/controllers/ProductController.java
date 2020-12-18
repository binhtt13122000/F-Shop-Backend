package com.dev.fshop.controller;

import com.dev.fshop.entities.ProductEntity;
import com.dev.fshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path = "/products")
    @CrossOrigin
    public ResponseEntity<ProductEntity> createNewProduct(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok().body(productService.createNewProduct(productEntity));
    }

    @PutMapping(path = "/products/{proId}")
    @CrossOrigin
    public ResponseEntity<ProductEntity> updateProductExisted(@PathVariable String proId, @RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok().body(productService.updateProductExisted(proId, productEntity));
    }

    @DeleteMapping(path = "/products/{proId}")
    @CrossOrigin
    public boolean deleteProductExisted(@PathVariable String proId) {
        productService.deleteProductExisted(proId);
        return true;
    }

    @GetMapping(path = "/products")
    @CrossOrigin
    public ResponseEntity<List<ProductEntity>> findProducts(
            @RequestParam Optional<Integer> star,
            @RequestParam Optional<Date> createAt,
            @RequestParam Optional<String> type,
            @RequestParam(name = "priceFrom") float priceFrom, @RequestParam(name = "priceTo") float priceTo,
            @RequestParam Optional<String> proId,
            @RequestParam Optional<String> proName) {
        return null;
    }



}