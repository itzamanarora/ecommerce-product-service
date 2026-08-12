package com.eCommerce.productService.dto.categoryDTO;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {
    private UUID categoryId;
    private String name;
    private String slug;
    private String description;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
