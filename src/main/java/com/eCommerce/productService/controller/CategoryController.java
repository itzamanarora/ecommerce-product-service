package com.eCommerce.productService.controller;

import com.eCommerce.productService.dto.categoryDTO.CategoryRequestDTO;
import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;
import com.eCommerce.productService.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

//    @GetMapping
//    public ResponseEntity<CategoryResponseDTO> findByCategoryName(@RequestParam final String categoryName) {
//        return ResponseEntity.status(HttpStatus.OK).body(categoryServiceImpl.findByCategoryName(categoryName));
//    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody final CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryServiceImpl.saveCategory(categoryRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryServiceImpl.getAllCategories());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") final UUID categoryId) {
        categoryServiceImpl.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
