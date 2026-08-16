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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        log.info("Creating category with name: {}", categoryRequestDTO.getName());
        Category category = CategoryDTOMapper.mapToCategory(categoryRequestDTO);
        category.setSlug(categoryRequestDTO.getSlug().toLowerCase());
        category.setActive(true);
        category.setDelete(false);

        try {
            Category savedCategory = categoryRepository.saveAndFlush(category);
            log.info("Category created successfully with id: {}", savedCategory.getCategoryId());

            return CategoryDTOMapper.mapToCategoryResponse(savedCategory);
        } catch (DataIntegrityViolationException ex) {
            throw resolveConflict(ex, categoryRequestDTO.getName(), categoryRequestDTO.getSlug());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return CategoryDTOMapper.categoryListDTOResponse(categoryRepository.findAllByIsDeleteFalse());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponseDTO getCategoryById(final UUID categoryId) {
        Category category = categoryRepository.findByCategoryIdAndIsDeleteFalse(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found with id: " + categoryId)
        );
        return CategoryDTOMapper.mapToCategoryResponse(category);
    }

    @Transactional
    @Override
    public CategoryResponseDTO updateCategory(final UUID categoryId, final CategoryRequestDTO categoryRequestDTO) {
        log.info("Updating category with Id: {}", categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found with this Id: " + categoryId)
        );
        if (StringUtils.hasText(categoryRequestDTO.getName())) {
            category.setName(categoryRequestDTO.getName());
        }
        if (StringUtils.hasText(categoryRequestDTO.getSlug())) {
            category.setSlug(categoryRequestDTO.getSlug());
        }
        if (categoryRequestDTO.getDescription() != null) {
            category.setDescription(categoryRequestDTO.getDescription());
        }

        try {
            categoryRepository.saveAndFlush(category);
        } catch (DataIntegrityViolationException ex) {
            throw resolveConflict(ex, categoryRequestDTO.getName(), categoryRequestDTO.getSlug());
        }

        log.info("Category updated successfully with Id: {}", categoryId);
        return CategoryDTOMapper.mapToCategoryResponse(category);
    }

    @Transactional
    @Override
    public void deleteById(UUID categoryId) {
        log.info("Deleting Category with category Id: {}", categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found with this Id: " + categoryId));
        category.setDelete(true);
        categoryRepository.save(category);
        log.info("Category successfully deleted with Id: {}", categoryId);
    }

    private CategoryAlreadyExistsException resolveConflict(
            DataIntegrityViolationException ex,
            String name,
            String slug) {

        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException hibernateEx) {
            String constraintName = hibernateEx.getConstraintName();
            if ("uk_categories_name".equals(constraintName)) {
                return new CategoryAlreadyExistsException("Category already exists with name: " + name);
            }
            if ("uk_categories_slug".equals(constraintName)) {
                return new CategoryAlreadyExistsException("Category already exists with slug: " + slug);
            }
        }
        return new CategoryAlreadyExistsException("Category with this name or slug already exists");
    }
}
