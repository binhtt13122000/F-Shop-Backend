package com.dev.fshop.controller;

import com.dev.fshop.entity.PromotionEntity;
import com.dev.fshop.services.PromotionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class PromotionController {
    @Autowired
    private PromotionServiceInterface promotionServiceInterface;

    @GetMapping(path = "/promotions")
    public List<PromotionEntity> getAllPromotions() {
        return promotionServiceInterface.getAllPromotions();
    }

    @GetMapping(path = "/promotions/{promotionId}")
    public  PromotionEntity findPromotionById(@PathVariable Integer promotionId) {
        return promotionServiceInterface.findPromotionByPromotionId(promotionId);
    }

    @GetMapping(path = "/promotions/{userId}")
    public List<PromotionEntity> getAllPromotionsByUserId(@PathVariable String userId) {
        return  promotionServiceInterface.getAllPromotionsByUserId(userId);
    }

    @PostMapping(path = "/promotions")
    public PromotionEntity createNewPromotion(@RequestBody PromotionEntity promotionEntity) {
        return  promotionServiceInterface.createNewPromotion(promotionEntity);
    }

    @DeleteMapping(path = "/promotions/{promotionId}")
    public boolean deletePromotionByPromotionId(@PathVariable Integer promotionId) {
        return promotionServiceInterface.deletePromotionById(promotionId);
    }
}
