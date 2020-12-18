package com.dev.fshop.repositories;


import com.dev.fshop.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

    public List<ProductEntity> findProductEntitiesByProName(String name);

    @Query("select u from ProductEntity u where u.proPrice > :priceFrom and u.proPrice < :priceTo")
    public List<ProductEntity> findProductEntitiesByProPrice(float priceFrom, float priceTo);

    public List<ProductEntity> findProductEntitiesByCategoryEntity(String type);

    @Query("select u from ProductEntity u where u.createAt > :date ")
    public List<ProductEntity> findNewProduct(Date date);

    @Query("select u.productEntity.proId from ReviewEntity u where u.star > :star ")
    public List<ProductEntity> findGoodProduct(Integer star);

    public boolean deleteProductEntitiesByProId(String proId);
//
//    @Transactional
//    public ProductEntity insertProductWithEntityManager(ProductEntity productEntity);
}
