package com.dev.fshop.repositories;

import com.dev.fshop.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    public List<Promotion> findPromotionsByAccount_UserId(String userId);

}
