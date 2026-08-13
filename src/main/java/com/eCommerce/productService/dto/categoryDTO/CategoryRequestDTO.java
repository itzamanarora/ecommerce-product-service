package com.eCommerce.productService.dto.categoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    @NotBlank(message = "Please provide a category name.")
    @Size(max = 100, message = "Category name must not be exceed 100 characters")
    private String name;

    @NotBlank(message = "Please provide a category slug.")
    @Size(max = 120, message = "Category slug must not be exceed 120 characters")
    private String slug;

    private String description;
}
