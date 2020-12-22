package com.dev.fshop.repositories;

import com.dev.fshop.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {

//    public Promotion findPromotionEntitiesByPromotionID(String promotionId);
//
//    public List<Promotion> getPromotionEntitiesByCustomerEntity(String userId);
//
//    @Query("update Promotion  u set u.status = :status where  u.promotionName = :promotionName")
//    public Promotion updateStatusPromotionByName(boolean status, String promotionName);
}
