package com.dev.fshop.repositories;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.entity.PromotionEntity;
import com.dev.fshop.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {

    public PromotionEntity findPromotionById(Integer promotionId);
    public List<PromotionEntity> findPromotionById();

    @Query("Select u.promotionName  from PromotionEntity u inner join CustomerEntity v on u.userId = v.userId")
    public List<PromotionEntity> findPromotionInUser(String userId);

    @Modifying(clearAutomatically = true)
    @Query("update PromotionEntity u set u.promotionName = :namePromo, u.promo = :promo, u.status = :status where u.promotionID = :promotionId")
    public PromotionEntity deleteAccount(Integer promotionId, String namePromo, float promo, boolean status );

    @Query("delete  from PromotionEntity u where u.promotionID = :promotionId")
    public PromotionEntity deletePromotion(Integer promotionId);

    @Transactional
    public PromotionEntity insertPromotionWithEntityManager(PromotionEntity promotionEntity);
}
