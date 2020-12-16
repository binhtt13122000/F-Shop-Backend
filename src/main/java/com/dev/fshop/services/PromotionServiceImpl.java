package com.dev.fshop.services;

import com.dev.fshop.entity.ProductEntity;
import com.dev.fshop.entity.PromotionEntity;
import com.dev.fshop.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionServiceImpl implements PromotionServiceInterface{
    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public List<PromotionEntity> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public PromotionEntity findPromotionByPromotionId(Integer promotionId) {
        return promotionRepository.findPromotionById(promotionId);
    }

    @Override
    public List<PromotionEntity> getAllPromotionsByUserId(String userId) {
        return promotionRepository.findPromotionInUser(userId);
    }

    @Override
    public PromotionEntity createNewPromotion(PromotionEntity promotionEntity) {
//        PromotionEntity checkExisted = promotionRepository.findById(promotionEntity.getPromotionID()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return promotionRepository.insertPromotionWithEntityManager(promotionEntity);
    }

    @Override
    public boolean deletePromotionById(Integer promotionId) {
        PromotionEntity checkExisted = promotionRepository.findById(promotionId).orElse(null);
        if(checkExisted == null) {
            return false;
        }
        promotionRepository.deletePromotion(promotionId);
        return true;
    }
}
