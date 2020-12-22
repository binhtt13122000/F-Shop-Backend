package com.dev.fshop.services;

import com.dev.fshop.entities.Promotion;

import java.util.List;

public interface PromotionService {
    //Get methods
    public List<Promotion> getAllPromotions();
    public Promotion findPromotionByPromotionId(String  promotionId);
    public List<Promotion> getAllPromotionsByUserId(String userId);

}
