package com.eCommerce.productService.repository;

import com.eCommerce.productService.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Category, UUID> {
    boolean existByCategoryCategoryId(UUID categoryId);
}
