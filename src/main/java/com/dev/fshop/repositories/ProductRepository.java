package com.dev.fshop.repositories;


import com.dev.fshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

//    public List<Product> findProductEntitiesByProName(String name);
//
//    @Query("select u from Product u where u.proPrice > :priceFrom and u.proPrice < :priceTo")
//    public List<Product> findProductEntitiesByProPrice(float priceFrom, float priceTo);
//
//    public List<Product> findProductEntitiesByCategoryEntity(String type);
//
//    @Query("select u from Product u where u.createAt > :date ")
//    public List<Product> findNewProduct(Date date);
//
//    @Query("select u.productEntity.proId from Review u where u.star > :star ")
//    public List<Product> findGoodProduct(Integer star);
//
//    public boolean deleteProductEntitiesByProId(String proId);
//
//    @Transactional
//    public ProductEntity insertProductWithEntityManager(ProductEntity productEntity);
}
