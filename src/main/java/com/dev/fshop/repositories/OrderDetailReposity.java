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

    public boolean deleteOrderByCustomer(String userId, List<OrderDetailEntity> orderDetail);

    @Query("Select v.orderItemId from OrderDetailEntity u inner join OrderItemEntity v on u.orderId = v.orderId where v.orderItemId > :orderItemId")
    public OrderItemEntity findOrder(String orderId, String orderItemId);

    @Query("DELETE from OrderItemEntity u where u.orderItemId = :orderItemId ")
    // Tìm kiếm bằng findOrder nếu có thì deleteItemInorder đó
    public boolean deleteItemInOrder(int orderId, int orderItemId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE OrderItemEntity u set u.orderItemPrice = :quantity where u.orderItemId = :orderItemId ")
    public OrderDetailEntity updateQuanProduct(Integer quanity, Integer orderItemId);

    @Transactional
    public OrderDetailEntity insertOrderWithEntityManager(OrderDetailEntity orderDetailEntity);

}
