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

import com.turkcell.spring_library.dto.author.AuthorResponse;
import com.turkcell.spring_library.dto.author.CreateAuthorRequest;
import com.turkcell.spring_library.dto.author.CreatedAuthorResponse;
import com.turkcell.spring_library.dto.author.ListAuthorResponse;
import com.turkcell.spring_library.dto.author.UpdateAuthorRequest;
import com.turkcell.spring_library.dto.author.UpdatedAuthorResponse;
import com.turkcell.spring_library.service.AuthorServiceImpl;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {
    private final AuthorServiceImpl authorServiceImpl;

    public AuthorsController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping
    public List<ListAuthorResponse> getAll() {
        return authorServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable UUID id) {
        return authorServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedAuthorResponse create(@RequestBody CreateAuthorRequest request) {
        return authorServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedAuthorResponse update(@PathVariable UUID id, @RequestBody UpdateAuthorRequest request) {
        return authorServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        authorServiceImpl.delete(id);
    }
}
