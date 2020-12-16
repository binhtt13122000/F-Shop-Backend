package com.dev.fshop.repositories;


import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{



    public List<ProductEntity> findAll();

    @Query("Select u from ProductEntity u where u.proPrice = :name")
    public List<ProductEntity> searchProductByName(String name);

    @Query("Select u from ProductEntity u where u.proPrice > :priceFrom and u.proPrice < :priceTo")
    public List<ProductEntity> searchProductByPrice(float priceFrom, float priceTo);

    @Query("Select u from ProductEntity u where u.proPrice = :type")
    public List<ProductEntity> searchProductByType(String type);

    @Query("Select u from ProductEntity u where u.createAt > :date")
    public List<ProductEntity> findNewProduct(Date date);

    @Query("Select u.star from ReviewEntity u inner join ProductEntity v on u.proId = v.proId where u.star > :star")
    public List<ProductEntity> findGoodProduct(Integer star);

    @Modifying(clearAutomatically = true)
    @Query("update ProductEntity u set  u.proName = :name, u.proPrice = :price, u.proQuantity = :quanity, u.proDescription = :description, u.discount = :discount where u.proId = :id ")
    public ProductEntity updateProduct(Integer id, String name, float price, Integer quantity, String description, float discount);

    @Query("delete from ProductEntity u where u.proId = :proId ")
    public boolean deleteItemInOrder(int proId);

    @Transactional
    public ProductEntity insertProductWithEntityManager(ProductEntity productEntity);
}
