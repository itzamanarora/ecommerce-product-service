package com.eCommerce.productService.controller;

import com.eCommerce.productService.dto.categoryDTO.CategoryResponseDTO;
import com.eCommerce.productService.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping()
    public ResponseEntity<CategoryResponseDTO> findByCategoryName(@RequestParam String search) {
        return new ResponseEntity<>(categoryService.findByCategoryName(search), HttpStatus.OK);
    }
}
