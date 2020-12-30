package com.dev.fshop.repositories;


import com.dev.fshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
    public List<Product> getProductsByStatus(int status);

}
