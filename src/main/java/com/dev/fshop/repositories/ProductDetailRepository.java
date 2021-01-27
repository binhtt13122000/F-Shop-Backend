package com.dev.fshop.repositories;

import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    @Query("select  u from ProductDetail  u where u.product.productId = :productId and u.proSize = :productSize")
    public ProductDetail findProductDetailByProductIdAndAndProSize(String productId, String productSize);
}
