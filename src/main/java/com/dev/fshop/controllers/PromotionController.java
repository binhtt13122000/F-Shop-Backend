package com.dev.fshop.controller;

import com.dev.fshop.entities.PromotionEntity;
import com.dev.fshop.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;


    @GetMapping(path = "/promotions/{promotionId}")
    public ResponseEntity<PromotionEntity> findPromotionById(@PathVariable String promotionId) {
        return ResponseEntity.ok().body(promotionService.findPromotionByPromotionId(promotionId));
    }

    @GetMapping(path = "/promotions")
    public ResponseEntity<List<PromotionEntity>> getAllPromotionsByUserId(@RequestParam(name = "userId") String userId) {
        return  ResponseEntity.ok().body(promotionService.getAllPromotionsByUserId(userId));
    }


}
