package com.dev.fshop.repositories;

import com.dev.fshop.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, String> {

//    public PromotionEntity findPromotionById(String promotionId);
//    public List<PromotionEntity> getAllPromotionByUserId(String userId);
//    public PromotionEntity updateStatusPromotion(boolean status);
}
