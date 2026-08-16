package com.eCommerce.productService.service;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO saveCategory(final CategoryRequestDTO categoryRequestDTO);

//    List<CategoryResponseDTO> getAllCategories(final Integer pageNo, final Integer pageSize, final String sortBy);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(final UUID categoryId);

    CategoryResponseDTO updateCategory(final UUID categoryId, final CategoryRequestDTO categoryRequestDTO);

    void deleteById(final UUID categoryId);
}
