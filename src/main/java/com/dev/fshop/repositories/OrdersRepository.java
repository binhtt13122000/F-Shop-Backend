package com.dev.fshop.repositories;


import com.dev.fshop.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

    @Query("select u.orders from OrderDetail u where u.product.productId = :productId and u.orders.account.userId = :userId and" +
            " u.orders.status >= :status")
    public List<Orders> getOrdersByProductIdAndUserIdAndStatus(String productId, String userId, int status);
}
