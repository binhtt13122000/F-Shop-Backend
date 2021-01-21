package com.dev.fshop.repositories;

import com.dev.fshop.supporters.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {
    public Page<CartDetail> getCartDetailsByCart_CartIdAndCart_Account_UserId(String cartId, String userId, Pageable pageable);

    @Query("select u from CartDetail u where u.cartSize = :cartSize and u.cart.cartId = :cartId and u.product.productId = :productId")
    public CartDetail getCartDetailByCartIdAndProductIdAndCartSize(String cartId, String productId, String cartSize);
}
