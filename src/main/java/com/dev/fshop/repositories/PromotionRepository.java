package com.dev.fshop.repositories;

import com.dev.fshop.entities.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, String> {

    public PromotionEntity findPromotionEntitiesByPromotionID(String promotionId);

    public List<PromotionEntity> getPromotionEntitiesByCustomerEntity(String userId);

    @Query("update PromotionEntity  u set u.status = :status where  u.promotionName = :promotionName")
    public PromotionEntity updateStatusPromotionByName(boolean status, String promotionName);
}
