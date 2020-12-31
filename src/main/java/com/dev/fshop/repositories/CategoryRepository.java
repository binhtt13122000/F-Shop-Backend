package com.dev.fshop.repositories;

import com.dev.fshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsCategoryByProTypeId(String id);
}
