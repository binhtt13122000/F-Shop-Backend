package com.dev.fshop.services;

import com.dev.fshop.entities.Account;
import com.dev.fshop.entities.Promotion;

import java.util.List;

public interface PromotionService {
    public List<Promotion> getPromotionsByUserId(String userId);

    public Promotion createPromotion(Promotion promotion, Account account);

    public Promotion updatePromotion(Promotion currentPromotion, Promotion newPromotion);

    public Promotion getPromotionByPromotionId(String promotionId);

    public boolean changeStatusPromotion(Promotion promotion, boolean status);
}
