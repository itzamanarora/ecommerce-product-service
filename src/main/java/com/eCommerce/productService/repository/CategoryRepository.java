package com.eCommerce.productService.repository;

import com.eCommerce.productService.models.Category;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByIsDeleteFalse();

    Optional<Category> findByCategoryIdAndIsDeleteFalse(UUID categoryId);
}
