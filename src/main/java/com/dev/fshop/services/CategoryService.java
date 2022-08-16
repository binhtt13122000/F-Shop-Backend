package com.dev.fshop.services;

import com.dev.fshop.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public Category findCategoryByCategoryId(String categoryId);

    public Category createNewCategory(Category category);

    public List<Category> checkCategoriesByCategoryName(String categoryName);

    public Page<Category> searchCategoriesWithCategoryName(String categoryName, Pageable pageable);
}
