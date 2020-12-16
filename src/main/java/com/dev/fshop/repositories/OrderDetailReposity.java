package com.dev.fshop.repositories;


import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailReposity extends JpaRepository<OrderDetailEntity, Integer> {

    @Query
    public boolean deleteOrderByCustomer(String userId, List<OrderDetailEntity> orderDetail);

    @Query("Select v.orderItemId, v.orderItemQuan, v.orderItemPrice  from OrderDetailEntity u inner join OrderItemEntity v on u.orderId = v.orderId where u.orderId = :orderId")
    public List<OrderItemEntity> findOrder(Integer orderId);

    @Query("DELETE from OrderItemEntity u where u.orderItemId = :orderItemId ")
    public boolean deleteItemInOrder(int orderItemId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE OrderItemEntity u set u.orderItemPrice = :quantity where u.orderItemId = :orderItemId ")
    public OrderDetailEntity updateQuanProduct(Integer quanity, Integer orderItemId);

    @Transactional
    public OrderDetailEntity insertOrderWithEntityManager(OrderDetailEntity orderDetailEntity);

}
