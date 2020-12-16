package com.dev.fshop.services;

import com.dev.fshop.entity.PromotionEntity;

import java.util.List;

public interface PromotionServiceInterface {
    //Get methods
    public List<PromotionEntity> getAllPromotions();
    public PromotionEntity findPromotionByPromotionId(Integer promotionId);
    public List<PromotionEntity> getAllPromotionsByUserId(String userId);


    //Post methods
    public PromotionEntity createNewPromotion(PromotionEntity promotionEntity);

    //Delete methods
    public boolean deletePromotionById(Integer promotionId);
}
