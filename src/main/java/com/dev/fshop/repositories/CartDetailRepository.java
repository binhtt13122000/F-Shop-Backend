package com.dev.fshop.repositories;

import com.dev.fshop.supporters.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {
    public List<CartDetail> getCartDetailsByCart_CartId(String cartId);
}
