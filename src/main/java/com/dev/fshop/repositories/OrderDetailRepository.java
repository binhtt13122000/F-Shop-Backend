package com.dev.fshop.repositories;

import com.dev.fshop.supporters.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    public List<OrderDetail> getOrderDetailsByOrders_OrderId(String orderId);
}
