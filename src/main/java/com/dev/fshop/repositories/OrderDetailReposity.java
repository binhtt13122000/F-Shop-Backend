package com.dev.fshop.repositories;


import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailReposity extends JpaRepository<OrderDetailEntity, Integer> {
    //public List<OrderItem> findByCustomer(String )
    public boolean deleteAllByCustomer(Integer userId, List<OrderDetailEntity> orderDetail);

}
