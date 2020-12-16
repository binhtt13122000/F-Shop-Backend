package com.dev.fshop.services;

import com.dev.fshop.entity.ProductEntity;
import com.dev.fshop.entity.PromotionEntity;

import java.util.List;

public interface PromotionServiceInterface {
    //Get methods
    public List<PromotionEntity> getAllPromotions();
    public ProductEntity findPromotionByPromotionId(Integer promotionId);
    public List<PromotionEntity> getAllPromotionByUserId(String userId);


    //Post methods
    public PromotionEntity createNewPromotion(PromotionEntity promotionEntity);

    //Delete methods
    public PromotionEntity deletePromotionById(Integer promotionId);
}
