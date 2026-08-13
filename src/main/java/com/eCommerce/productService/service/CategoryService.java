package com.eCommerce.productService.service;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO saveCategory(final CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO findByCategoryName(final String categoryName);

//    public List<CategoryResponseDTO> getAllCategories(Integer pageNo, Integer pageSize, String sortBy);

    List<CategoryResponseDTO> getAllCategories();

    void deleteById(final UUID categoryId);
}
