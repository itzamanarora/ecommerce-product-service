package com.eCommerce.productService.service;

import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;
import com.eCommerce.productService.exception.CategoryNotFoundException;
import com.eCommerce.productService.helper.CatgeoryDTOMapper;
import com.eCommerce.productService.models.Category;
import com.eCommerce.productService.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO findByCategoryName(String categoryName) {
        Category category = categoryRepository
                .findByName(categoryName)
                .orElseThrow(() -> {
                    log.warn("Category not found: {}", categoryName);
                    return new CategoryNotFoundException(
                            "Category Not Found: " + categoryName
                    );
                });
        return CatgeoryDTOMapper.mapToCategoryResponse(category);
    }
}
