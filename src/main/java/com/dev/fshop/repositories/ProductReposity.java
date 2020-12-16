package com.dev.fshop.repositories;


import com.dev.fshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReposity extends JpaRepository<ProductEntity, Integer>{

    public List<ProductEntity> findAll();
    @Query("Select u from ProductEntity u where u.proPrice = :name")
    public List<ProductEntity> searchProductByName(String name);
    @Query("Select u from ProductEntity u where u.proPrice > :priceFrom and u.proPrice < :priceTo")
    public List<ProductEntity> searchProductByPrice(float priceFrom, float priceTo);
    @Query("Select u from ProductEntity u where u.proPrice = :type")
    public List<ProductEntity> searchProductByType(String type);
}
