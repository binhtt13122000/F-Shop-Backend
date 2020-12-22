package com.dev.fshop.repositories;


import com.dev.fshop.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersReposity extends JpaRepository<Orders, String> {

//    public List<Orders> findOrdersEntityByCustomerEntity(String userId);
//
//    @Query("update Orders  u set u.status = :status where u.orderId = :orderId")
//    public Orders updateStatusOrder(String orderId, boolean status);
//
// //   public List<OrderDetail> buyProductByUser(String proId, String quantity, String userId);
//
//    @Query("Select SUM(u.orderTotal) from Orders u")
//    public float viewRevenue();
//
//    @Transactional
//    public OrdersEntity insertOrderWithEntityManager(OrdersEntity ordersEntity);



}
