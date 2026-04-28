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

import com.turkcell.spring_library.dto.bookcopy.BookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.CreateBookCopyRequest;
import com.turkcell.spring_library.dto.bookcopy.CreatedBookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.ListBookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.UpdateBookCopyRequest;
import com.turkcell.spring_library.dto.bookcopy.UpdatedBookCopyResponse;
import com.turkcell.spring_library.service.BookCopyServiceImpl;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopiesController {
    private final BookCopyServiceImpl bookCopyServiceImpl;

    public BookCopiesController(BookCopyServiceImpl bookCopyServiceImpl) {
        this.bookCopyServiceImpl = bookCopyServiceImpl;
    }

    @GetMapping
    public List<ListBookCopyResponse> getAll() {
        return bookCopyServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public BookCopyResponse getById(@PathVariable UUID id) {
        return bookCopyServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedBookCopyResponse create(@RequestBody CreateBookCopyRequest request) {
        return bookCopyServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedBookCopyResponse update(@PathVariable UUID id, @RequestBody UpdateBookCopyRequest request) {
        return bookCopyServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookCopyServiceImpl.delete(id);
    }
}
