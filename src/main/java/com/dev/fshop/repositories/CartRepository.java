package com.dev.fshop.repositories;

import com.dev.fshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    public List<Cart> getCartsByAccount_UserId(String userId);

    @Query("select u from Cart u where u.account.userId = :userId and u.status = :status")
    public List<Cart> getCartsByUserIdByUser(String userId, int status);

    @Query("select u from Cart u where (u.account.userId = :userId) and (:q is null  or u.cartDescription like :q)")
    public List<Cart> getCartsByParameterQByAdmin(String q, String userId);

    @Query("select u from Cart u where (u.account.userId = :userId) and (:q is null  or u.cartDescription like :q) and (u.status = :status)")
    public List<Cart> getCartsByParameterQByUser(int status, String q, String userId);

    @Query("select u from Cart  u where (u.account.userId = :userId) and ((:dateFrom is null and :dateTo is null) or " +
            "(:dateFrom is not  null and :dateTo is not null and u.createTime between :dateFrom and :dateTo)) and " +
            "((:priceFrom is null and :priceTo is null ) or (:priceFrom is not null and :priceTo is not null and u.cartTotal >= :priceFrom and " +
            "u.cartTotal <= :priceTo))")
    public List<Cart> getCartsByParametersByAdmin(String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo);

    @Query("select u from Cart u where u.account.userId = :userId and u.status = :status  and ((:dateFrom is null and :dateTo is null) or " +
            "(:dateFrom is not  null and :dateTo is not null and u.createTime between :dateFrom and :dateTo)) and " +
            "((:priceFrom is null and :priceTo is null ) or (:priceFrom is not null and :priceTo is not null and u.cartTotal >= :priceFrom and " +
            "u.cartTotal <= :priceTo))")
    public List<Cart> getCartsByParametersByUser(int status, String userId, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo);

}
