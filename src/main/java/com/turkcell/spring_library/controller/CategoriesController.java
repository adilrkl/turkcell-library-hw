package com.turkcell.spring_library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_library.dto.category.CategoryResponse;
import com.turkcell.spring_library.dto.category.CreateCategoryRequest;
import com.turkcell.spring_library.dto.category.CreatedCategoryResponse;
import com.turkcell.spring_library.dto.category.ListCategoryResponse;
import com.turkcell.spring_library.dto.category.UpdateCategoryRequest;
import com.turkcell.spring_library.dto.category.UpdatedCategoryResponse;
import com.turkcell.spring_library.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoriesController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return categoryServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable UUID id) {
        return categoryServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return categoryServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedCategoryResponse update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        return categoryServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        categoryServiceImpl.delete(id);
    }
}
