package com.dev.fshop.repositories;


import com.dev.fshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    public Page<Product> getProductsByStatus(int status, Pageable pageable);

    @Query("select u from Product u where (:q is null or u.productName like :q) or (:q is null or u.category.proTypeName like :q)")
    public Page<Product> searchProductsByParameterQWithAdmin(String q, Pageable pageable);

    @Query("select u from Product u where ((:q is null or u.productName like :q) or (:q is null or u.category.proTypeName like :q)) and u.status = :status")
    public Page<Product> searchProductsByParameterQWithUser(int status, String q, Pageable pageable);

    @Query("select u from Product  u where (:productName is null or u.productName like :productName) and (:categoryName is null or u.category.proTypeName like :categoryName)" +
            " and ((:realPriceFrom is null and :realPriceTo is null) or (:realPriceFrom is not null and :realPriceTo is not null and u.realPrice >= :realPriceFrom and" +
            " u.realPrice <= :realPriceTo)) and ((:dateFrom is null and :dateTo is null) or (:dateFrom is not null and :dateTo is not null and " +
            "u.createAt between :dateFrom and :dateTo)) and u.status = :status")
    public Page<Product> searchProductsByParametersWithUser(int status, String productName, String categoryName,
                                                            Float realPriceFrom, Float realPriceTo,
                                                            Date dateFrom, Date dateTo, Pageable pageable);

    @Query("select u from Product  u where (:productName is null or u.productName like :productName) and (:categoryName is null or u.category.proTypeName like :categoryName)" +
            " and ((:realPriceFrom is null and :realPriceTo is null) or (:realPriceFrom is not null and :realPriceTo is not null and u.realPrice >= :realPriceFrom and" +
            " u.realPrice <= :realPriceTo)) and ((:dateFrom is null and :dateTo is null) or (:dateFrom is not null and :dateTo is not null and " +
            " u.createAt between :dateFrom and :dateTo)) and ((:statusProduct = 2) or (u.status = :statusProduct))")
    public Page<Product> searchProductsByParametersWithAdmin(String productName, String categoryName,
                                                             Float realPriceFrom, Float realPriceTo,
                                                             Date dateFrom, Date dateTo, int statusProduct, Pageable pageable);

}
