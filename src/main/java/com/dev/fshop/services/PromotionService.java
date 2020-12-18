package com.dev.fshop.services;

import com.dev.fshop.entities.PromotionEntity;

import java.util.List;

public interface PromotionService {
    //Get methods
    public List<PromotionEntity> getAllPromotions();
    public PromotionEntity findPromotionByPromotionId(String  promotionId);
    public List<PromotionEntity> getAllPromotionsByUserId(String userId);

}
