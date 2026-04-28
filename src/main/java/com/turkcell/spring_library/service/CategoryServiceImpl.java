package com.turkcell.spring_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.category.CategoryResponse;
import com.turkcell.spring_library.dto.category.CreateCategoryRequest;
import com.turkcell.spring_library.dto.category.CreatedCategoryResponse;
import com.turkcell.spring_library.dto.category.ListCategoryResponse;
import com.turkcell.spring_library.dto.category.UpdateCategoryRequest;
import com.turkcell.spring_library.dto.category.UpdatedCategoryResponse;
import com.turkcell.spring_library.entity.Category;
import com.turkcell.spring_library.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

    public CategoryResponse getById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category bulunamadi: " + id));

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        category = categoryRepository.save(category);

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public UpdatedCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category bulunamadi: " + id));

        category.setName(request.getName());
        category = categoryRepository.save(category);

        UpdatedCategoryResponse response = new UpdatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    public void delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category bulunamadi: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
