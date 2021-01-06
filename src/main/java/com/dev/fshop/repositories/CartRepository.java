package com.dev.fshop.repositories;

import com.dev.fshop.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    public Page<Cart> getCartsByAccount_UserId(String userId, Pageable pageable);

    @Query("select u from Cart u where u.account.userId = :userId and u.status = :status")
    public Page<Cart> getCartsByUserIdByUser(String userId, int status, Pageable pageable);

    @Query("select u from Cart u where (u.account.userId = :userId) and (:q is null  or u.cartDescription like :q)")
    public Page<Cart> getCartsByParameterQByAdmin(String q, String userId, Pageable pageable);

    @Query("select u from Cart u where (u.account.userId = :userId) and (:q is null  or u.cartDescription like :q) and (u.status = :status)")
    public Page<Cart> getCartsByParameterQByUser(int status, String q, String userId, Pageable pageable);

    @Query("select u from Cart  u where (u.account.userId = :userId) and ((:dateFrom is null and :dateTo is null) or " +
            "(:dateFrom is not  null and :dateTo is not null and u.createTime between :dateFrom and :dateTo)) and " +
            "((:priceFrom is null and :priceTo is null ) or (:priceFrom is not null and :priceTo is not null and u.cartTotal >= :priceFrom and " +
            "u.cartTotal <= :priceTo))")
    public Page<Cart> getCartsByParametersByAdmin(String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo, Pageable pageable);

    @Query("select u from Cart u where u.account.userId = :userId and u.status = :status  and ((:dateFrom is null and :dateTo is null) or " +
            "(:dateFrom is not  null and :dateTo is not null and u.createTime between :dateFrom and :dateTo)) and " +
            "((:priceFrom is null and :priceTo is null ) or (:priceFrom is not null and :priceTo is not null and u.cartTotal >= :priceFrom and " +
            "u.cartTotal <= :priceTo))")
    public Page<Cart> getCartsByParametersByUser(int status, String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo, Pageable pageable);

}
