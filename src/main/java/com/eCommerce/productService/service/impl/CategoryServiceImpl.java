package com.eCommerce.productService.service.impl;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;
import com.eCommerce.productService.exception.CategoryAlreadyExistsException;
import com.eCommerce.productService.exception.CategoryNotFoundException;
import com.eCommerce.productService.helper.CategoryDTOMapper;
import com.eCommerce.productService.models.Category;
import com.eCommerce.productService.repository.CategoryRepository;
import com.eCommerce.productService.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO findByCategoryName(String categoryName) {
        Category category = categoryRepository
                .findByName(categoryName)
                .orElseThrow(() -> {
                    log.warn("Category not found with name: {}", categoryName);
                    throw new CategoryNotFoundException(
                            "Category Not Found: " + categoryName
                    );
                });
        return CategoryDTOMapper.mapToCategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        String categoryName = categoryRequestDTO.getName();
        log.info("Creating category with name: {}", categoryName);

        if (categoryRepository.findByName(categoryName).isPresent()) {
            log.warn("Category already exists with name: {}", categoryName);
            throw new CategoryAlreadyExistsException("Category with this name, Already Exists!");
        }
        Category category = CategoryDTOMapper.mapToCategory(categoryRequestDTO);
        category.setActive(true);
        category.setDelete(false);
        Category savedCategory = categoryRepository.save(category);

        log.info("Category created successfully with id: {}", savedCategory.getCategoryId());
        return CategoryDTOMapper.mapToCategoryResponse(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return CategoryDTOMapper.categoryListDTOResponse(categoryRepository.findAll());
    }
}
