package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Promotion;
import com.dev.fshop.repositories.PromotionRepository;
import com.dev.fshop.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getPromotionsByUserId(String userId) {
        return promotionRepository.findPromotionsByAccount_UserId(userId);
    }

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }
}
