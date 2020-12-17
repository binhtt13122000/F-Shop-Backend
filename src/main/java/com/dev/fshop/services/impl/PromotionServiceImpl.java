package com.dev.fshop.services.impl;

import com.dev.fshop.entity.PromotionEntity;
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
    public List<PromotionEntity> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public PromotionEntity findPromotionByPromotionId(String promotionId) {
        return promotionRepository.findPromotionById(promotionId);
    }

    @Override
    public List<PromotionEntity> getAllPromotionsByUserId(String userId) {
        return promotionRepository.getAllPromotionByUserId(userId);
    }



}
