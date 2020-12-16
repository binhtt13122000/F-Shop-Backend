package com.dev.fshop.controller;

import com.dev.fshop.entity.PromotionEntity;
import com.dev.fshop.services.PromotionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class PromotionController {
    @Autowired
    private PromotionServiceInterface promotionServiceInterface;

    @GetMapping(path = "/promotions")
    public ResponseEntity<List<PromotionEntity>> getAllPromotions() {
        return ResponseEntity.ok().body(promotionServiceInterface.getAllPromotions());
    }

    @GetMapping(path = "/promotions/{promotionId}")
    public  ResponseEntity<PromotionEntity> findPromotionById(@PathVariable Integer promotionId) {
        return ResponseEntity.ok().body(promotionServiceInterface.findPromotionByPromotionId(promotionId));
    }

    @GetMapping(path = "/promotions/{userId}")
    public ResponseEntity<List<PromotionEntity>> getAllPromotionsByUserId(@PathVariable String userId) {
        return  ResponseEntity.ok().body(promotionServiceInterface.getAllPromotionsByUserId(userId));
    }

    @PostMapping(path = "/promotions")
    public ResponseEntity<PromotionEntity> createNewPromotion(@RequestBody PromotionEntity promotionEntity) {
        return  ResponseEntity.ok().body(promotionServiceInterface.createNewPromotion(promotionEntity));
    }

    @DeleteMapping(path = "/promotions/{promotionId}")
    public boolean deletePromotionByPromotionId(@PathVariable Integer promotionId) {
        return promotionServiceInterface.deletePromotionById(promotionId);
    }
}
