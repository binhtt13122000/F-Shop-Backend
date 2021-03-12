package com.dev.fshop.services;

import com.dev.fshop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public Category findCategoryByCategoryId(String categoryId);

    public Category createNewCategory(Category category);

    public List<Category> searchCategoriesByCategoryName(String categoryName);
}
