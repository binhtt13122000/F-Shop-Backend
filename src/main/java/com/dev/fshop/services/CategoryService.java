package com.dev.fshop.services;

import com.dev.fshop.entities.Category;
import org.springframework.stereotype.Service;

public interface CategoryService {
    public Category findCategoryByCategoryId(String categoryId);
}
