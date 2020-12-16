package com.dev.fshop.repositories;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {

    public PromotionEntity findPromotionByName(String promotionName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE PromotionEntity u set u.promotionName = :namePromo, u.promo = :promo, u.status = :status where u.promotionID = :promotionId")
    public PromotionEntity deleteAccount(Integer promotionId, String namePromo, float promo, boolean status );

    @Modifying(clearAutomatically = true)
    @Query("delete  from PromotionEntity u where u.promotionID = :promotionId")
    public PromotionEntity deletePromotion(Integer promotionId);

    @Transactional
    public PromotionEntity insertPromotionWithEntityManager(PromotionEntity promotionEntity);
}
