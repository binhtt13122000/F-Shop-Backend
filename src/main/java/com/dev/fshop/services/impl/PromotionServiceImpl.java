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
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findPromotionByPromotionId(String promotionId) {
//        return promotionRepository.findPromotionEntitiesByPromotionID(promotionId);
        return null;
    }

    @Override
    public List<Promotion> getAllPromotionsByUserId(String userId) {
//        return promotionRepository.getPromotionEntitiesByCustomerEntity(userId);
        return null;

    }



}
