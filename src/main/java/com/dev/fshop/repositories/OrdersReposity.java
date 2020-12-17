package com.dev.fshop.repositories;


import com.dev.fshop.entity.OrdersEntity;
import com.dev.fshop.embedded.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrdersReposity extends JpaRepository<OrdersEntity, String> {

    public List<OrdersEntity> findListOrderItemByUserId(String userId);
    public OrdersEntity updateStatusOrder(String orderId, boolean status);
    @Query()
    public List<OrderDetail> buyProductByUser(String proId, String quantity, String userId);
    @Query("Select SUM(u.orderTotal) from OrdersEntity u")
    public float viewRevenue();
    @Transactional
    public OrdersEntity insertOrderWithEntityManager(OrdersEntity ordersEntity);



}
