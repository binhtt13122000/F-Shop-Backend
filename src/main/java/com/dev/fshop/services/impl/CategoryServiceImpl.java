package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Category;
import com.dev.fshop.repositories.CategoryRepository;
import com.dev.fshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByCategoryId(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category createNewCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> searchCategoriesByCategoryName(String categoryName) {
        return categoryRepository.searchCategoriesByCategoryName(categoryName);
    }
}
