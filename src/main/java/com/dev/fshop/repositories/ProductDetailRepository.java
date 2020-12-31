package com.dev.fshop.repositories;

import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
    public ProductDetail findProductDetailByProduct_ProductIdAndAndProSize(String proId, String proSize);
}
