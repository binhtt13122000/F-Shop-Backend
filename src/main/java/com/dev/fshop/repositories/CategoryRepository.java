package com.dev.fshop.repositories;

import com.dev.fshop.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsCategoryByProTypeId(String id);

    @Query("select u from Category  u where u.proTypeName = :categoryName")
    public List<Category> searchCategoriesByCategoryName(String categoryName);

    @Query("select u from Category  u where (:categoryName is null) or (u.proTypeName like :categoryName)")
    public Page<Category> searchCategoriesWithCategoryName(String categoryName, Pageable pageable);
}
