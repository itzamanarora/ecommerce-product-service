package com.eCommerce.productService.service;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);

    public CategoryResponseDTO findByCategoryName(String categoryName);

//    public List<CategoryResponseDTO> getAllCategories(Integer pageNo, Integer pageSize, String sortBy);

    public List<CategoryResponseDTO> getAllCategories();
}
