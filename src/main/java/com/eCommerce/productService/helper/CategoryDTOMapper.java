package com.eCommerce.productService.helper;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;
import com.eCommerce.productService.models.Category;
import java.util.List;

public class CategoryDTOMapper {

    public static Category mapToCategory(CategoryRequestDTO categoryRequestDTO) {
        return new Category().builder()
                .name(categoryRequestDTO.getName())
                .slug(categoryRequestDTO.getSlug())
                .description(categoryRequestDTO.getDescription()).build();
    }

    public static CategoryResponseDTO mapToCategoryResponse(Category category) {
        return new CategoryResponseDTO().builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .isActive(category.isActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt()).build();
    }

    public static List<CategoryResponseDTO> categoryListDTOResponse(List<Category> categories) {
        return categories.stream()
                .map(CategoryDTOMapper::mapToCategoryResponse)
                .toList();
    }
}
