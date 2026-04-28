package com.turkcell.spring_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.book.BookResponse;
import com.turkcell.spring_library.dto.book.CreateBookRequest;
import com.turkcell.spring_library.dto.book.CreatedBookResponse;
import com.turkcell.spring_library.dto.book.ListBookResponse;
import com.turkcell.spring_library.dto.book.UpdateBookRequest;
import com.turkcell.spring_library.dto.book.UpdatedBookResponse;
import com.turkcell.spring_library.entity.Author;
import com.turkcell.spring_library.entity.Book;
import com.turkcell.spring_library.entity.Category;
import com.turkcell.spring_library.repository.AuthorRepository;
import com.turkcell.spring_library.repository.BookRepository;
import com.turkcell.spring_library.repository.CategoryRepository;

@Service
public class BookServiceImpl {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ListBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        List<ListBookResponse> responseList = new ArrayList<>();

        for (Book book : books) {
            ListBookResponse response = new ListBookResponse();
            response.setId(book.getId());
            response.setName(book.getName());
            response.setPage(book.getPage());
            response.setIsbn(book.getIsbn());
            if (book.getAuthor() != null) {
                response.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            }
            if (book.getCategory() != null) {
                response.setCategoryName(book.getCategory().getName());
            }
            responseList.add(response);
        }

        return responseList;
    }

    public BookResponse getById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book bulunamadi: " + id));

        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setPage(book.getPage());
        response.setIsbn(book.getIsbn());
        if (book.getAuthor() != null) {
            response.setAuthorId(book.getAuthor().getId());
            response.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
        }
        if (book.getCategory() != null) {
            response.setCategoryId(book.getCategory().getId());
            response.setCategoryName(book.getCategory().getName());
        }
        return response;
    }

    public CreatedBookResponse create(CreateBookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author bulunamadi: " + request.getAuthorId()));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category bulunamadi: " + request.getCategoryId()));

        Book book = new Book();
        book.setName(request.getName());
        book.setPage(request.getPage());
        book.setIsbn(request.getIsbn());
        book.setAuthor(author);
        book.setCategory(category);

        book = bookRepository.save(book);

        CreatedBookResponse response = new CreatedBookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setPage(book.getPage());
        response.setIsbn(book.getIsbn());
        response.setAuthorId(book.getAuthor().getId());
        response.setCategoryId(book.getCategory().getId());
        return response;
    }

    public UpdatedBookResponse update(UUID id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book bulunamadi: " + id));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author bulunamadi: " + request.getAuthorId()));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category bulunamadi: " + request.getCategoryId()));

        book.setName(request.getName());
        book.setPage(request.getPage());
        book.setIsbn(request.getIsbn());
        book.setAuthor(author);
        book.setCategory(category);

        book = bookRepository.save(book);

        UpdatedBookResponse response = new UpdatedBookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setPage(book.getPage());
        response.setIsbn(book.getIsbn());
        response.setAuthorId(book.getAuthor().getId());
        response.setCategoryId(book.getCategory().getId());
        return response;
    }

    public void delete(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book bulunamadi: " + id);
        }
        bookRepository.deleteById(id);
    }
}
