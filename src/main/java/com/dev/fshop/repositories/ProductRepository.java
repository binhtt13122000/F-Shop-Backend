package com.dev.fshop.repositories;


import com.dev.fshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
    public List<Product> getProductsByStatus(int status);

    @Query("select u from Product u where (:q is null or u.productName like :q) or (:q is null or u.category.proTypeName like :q)")
    public List<Product> searchProductsByParameterQWithAdmin(String q);

    @Query("select u from Product u where ((:q is null or u.productName like :q) or (:q is null or u.category.proTypeName like :q)) and u.status = :status")
    public List<Product> searchProductsByParameterQWithUser(int status, String q);

    @Query("select u from Product  u where (:productName is null or u.productName like :productName) and (:categoryName is null or u.category.proTypeName like :categoryName)" +
            " and ((:realPriceFrom is null and :realPriceTo is null) or (:realPriceFrom is not null and :realPriceTo is not null and u.realPrice >= :realPriceFrom and" +
            " u.realPrice <= :realPriceTo)) and ((:dateFrom is null and :dateTo is null) or (:dateFrom is not null and :dateTo is not null and " +
            "u.createAt between :dateFrom and :dateTo)) and u.status = :status")
    public List<Product> searchProductsByParametersWithUser(int status, String productName, String categoryName,
                                                            Float realPriceFrom, Float realPriceTo,
                                                            Date dateFrom, Date dateTo);

    @Query("select u from Product  u where (:productName is null or u.productName like :productName) and (:categoryName is null or u.category.proTypeName like :categoryName)" +
            " and ((:realPriceFrom is null and :realPriceTo is null) or (:realPriceFrom is not null and :realPriceTo is not null and u.realPrice >= :realPriceFrom and" +
            " u.realPrice <= :realPriceTo)) and ((:dateFrom is null and :dateTo is null) or (:dateFrom is not null and :dateTo is not null and " +
            " u.createAt between :dateFrom and :dateTo))")
    public List<Product> searchProductsByParametersWithAdmin(String productName, String categoryName,
                                                             Float realPriceFrom, Float realPriceTo,
                                                             Date dateFrom, Date dateTo);

}