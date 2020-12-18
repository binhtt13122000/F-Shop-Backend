package com.dev.fshop.controllers;

import com.dev.fshop.entity.ProductEntity;
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
//    @Autowired
//    private ProductService productService;
//
//    @PostMapping(path = "/products")
//    public ResponseEntity<ProductEntity> createNewProduct(@RequestBody ProductEntity productEntity) {
//        return ResponseEntity.ok().body(productService.createNewProduct(productEntity));
//    }
//
//    @PutMapping(path = "/products/{proId}")
//    public ResponseEntity<ProductEntity> updateProductExisted(@PathVariable String proId, @RequestBody ProductEntity productEntity) {
//        return ResponseEntity.ok().body(productService.updateProductExisted(proId, productEntity));
//    }
//
//    @DeleteMapping(path = "/products/{proId}")
//    public boolean deleteProductExisted(@PathVariable String proId) {
//        productService.deleteProductExisted(proId);
//        return true;
//    }
//
//    @GetMapping(path = "/products")
//    public ResponseEntity<List<ProductEntity>> findProducts(
//            @RequestParam Optional<Integer> star,
//            @RequestParam Optional<Date> createAt,
//            @RequestParam Optional<String> type,
//            @RequestParam(name = "priceFrom") float priceFrom, @RequestParam(name = "priceTo") float priceTo,
//            @RequestParam Optional<String> proId,
//            @RequestParam Optional<String> proName) {
//        return null;
//    }



}
