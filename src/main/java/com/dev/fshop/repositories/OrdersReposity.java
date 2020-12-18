package com.dev.fshop.repositories;


import com.dev.fshop.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersReposity extends JpaRepository<OrdersEntity, String> {

    public List<OrdersEntity> findOrdersEntityByCustomerEntity(String userId);

    @Query("update OrdersEntity  u set u.status = :status where u.orderId = :orderId")
    public OrdersEntity updateStatusOrder(String orderId, boolean status);

 //   public List<OrderDetail> buyProductByUser(String proId, String quantity, String userId);

    @Query("Select SUM(u.orderTotal) from OrdersEntity u")
    public float viewRevenue();
//
//    @Transactional
//    public OrdersEntity insertOrderWithEntityManager(OrdersEntity ordersEntity);



}
