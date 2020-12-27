package com.dev.fshop.services;

import com.dev.fshop.entities.Promotion;

import java.util.List;

public interface PromotionService {
    public List<Promotion> getPromotionsByUserId(String userId);
    public Promotion createPromotion(Promotion promotion);
    public Promotion updatePromotion(Promotion promotion);
}
