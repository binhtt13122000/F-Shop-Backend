package com.dev.fshop.repositories;


import com.dev.fshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

//    public List<ProductEntity> searchProductByName(String name);
//    public List<ProductEntity> searchProductByPrice(float priceFrom, float priceTo);
//    public List<ProductEntity> searchProductByType(String type);
//    public List<ProductEntity> findNewProduct(Date date);
//    public List<ProductEntity> findGoodProduct(Integer star);
//    public ProductEntity updateProduct(ProductEntity productEntity, String proId);
//    public boolean deleteProductInOrder(String proId);
//
//    @Transactional
//    public ProductEntity insertProductWithEntityManager(ProductEntity productEntity);
}
