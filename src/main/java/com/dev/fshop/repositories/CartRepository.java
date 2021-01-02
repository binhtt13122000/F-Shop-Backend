package com.dev.fshop.repositories;

import com.dev.fshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    public List<Cart> getCartsByAccount_UserId(String userId);
}
