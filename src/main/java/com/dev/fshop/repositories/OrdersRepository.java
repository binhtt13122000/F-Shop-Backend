package com.dev.fshop.repositories;


import com.dev.fshop.entities.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

    public Orders getOrdersByOrderIdAndAccount_UserIdAndStatusGreaterThanEqual(String orderId, String userId, int status);

    @Query("select u from Orders u where (:userId is null  or u.account.userId = :userId) and ((:dateFrom is null and :dateTo is null) or " +
            "(u.createAt between :dateFrom and :dateTo)) and u.status >= :status")
    public Page<Orders> getAllListOrdersByParameters(String userId, Date dateFrom, Date dateTo, int status, Pageable pageable);

    @Query("select u from Orders u where (:userId is null  or u.account.userId = :userId) and ((:dateFrom is null and :dateTo is null) or " +
            "(u.createAt between :dateFrom and :dateTo)) and u.status = :status")
    public Page<Orders> getListOrdersByParametersAndStatus(String userId, Date dateFrom, Date dateTo, int status, Pageable pageable);

    @Query("select u.orders from OrderDetail u where u.product.productId = :productId and u.orders.account.userId = :userId and" +
            " u.orders.status >= :status")
    public List<Orders> getOrdersByProductIdAndUserIdAndStatus(String productId, String userId, int status);

    @Query("select u from Orders u")
    public Page<Orders> getAllOrderByUserIdWithAdmin(Pageable pageable);

    @Query("select u from Orders u where (:userId is null or u.account.userId = :userId) and u.status >= :status")
    public Page<Orders> getAllOrderByUserIdWithUser(String userId, int status, Pageable pageable);

    @Query("select u from Orders u where (:userId is null or u.account.userId = :userId) and u.status = :status")
    public Page<Orders> getAllOrderByUserIdAndStatusWithUser(String userId, int status, Pageable pageable);
}
