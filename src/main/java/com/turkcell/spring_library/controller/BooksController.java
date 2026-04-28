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

import com.turkcell.spring_library.dto.book.BookResponse;
import com.turkcell.spring_library.dto.book.CreateBookRequest;
import com.turkcell.spring_library.dto.book.CreatedBookResponse;
import com.turkcell.spring_library.dto.book.ListBookResponse;
import com.turkcell.spring_library.dto.book.UpdateBookRequest;
import com.turkcell.spring_library.dto.book.UpdatedBookResponse;
import com.turkcell.spring_library.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookServiceImpl bookServiceImpl;

    public BooksController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping
    public List<ListBookResponse> getAll() {
        return bookServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable UUID id) {
        return bookServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedBookResponse create(@RequestBody CreateBookRequest request) {
        return bookServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedBookResponse update(@PathVariable UUID id, @RequestBody UpdateBookRequest request) {
        return bookServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookServiceImpl.delete(id);
    }
}
