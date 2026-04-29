package com.turkcell.spring_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.bookcopy.BookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.CreateBookCopyRequest;
import com.turkcell.spring_library.dto.bookcopy.CreatedBookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.ListBookCopyResponse;
import com.turkcell.spring_library.dto.bookcopy.UpdateBookCopyRequest;
import com.turkcell.spring_library.dto.bookcopy.UpdatedBookCopyResponse;
import com.turkcell.spring_library.entity.Book;
import com.turkcell.spring_library.entity.BookCopy;
import com.turkcell.spring_library.entity.enums.BookCopyStatus;
import com.turkcell.spring_library.exception.BusinessException;
import com.turkcell.spring_library.repository.BookCopyRepository;
import com.turkcell.spring_library.repository.BookRepository;

@Service
public class BookCopyServiceImpl {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyServiceImpl(BookCopyRepository bookCopyRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    public List<ListBookCopyResponse> getAll() {
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        List<ListBookCopyResponse> responseList = new ArrayList<>();

        for (BookCopy bookCopy : bookCopies) {
            ListBookCopyResponse response = new ListBookCopyResponse();
            response.setId(bookCopy.getId());
            if (bookCopy.getBook() != null) {
                response.setBookId(bookCopy.getBook().getId());
                response.setBookName(bookCopy.getBook().getName());
            }
            response.setStatus(bookCopy.getStatus());
            responseList.add(response);
        }

        return responseList;
    }

    public BookCopyResponse getById(UUID id) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("BookCopy bulunamadi: " + id));

        BookCopyResponse response = new BookCopyResponse();
        response.setId(bookCopy.getId());
        if (bookCopy.getBook() != null) {
            response.setBookId(bookCopy.getBook().getId());
            response.setBookName(bookCopy.getBook().getName());
        }
        response.setStatus(bookCopy.getStatus());
        return response;
    }

    public CreatedBookCopyResponse create(CreateBookCopyRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BusinessException("Book bulunamadi: " + request.getBookId()));

        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setStatus(request.getStatus() != null ? request.getStatus() : BookCopyStatus.AVAILABLE);

        bookCopy = bookCopyRepository.save(bookCopy);

        CreatedBookCopyResponse response = new CreatedBookCopyResponse();
        response.setId(bookCopy.getId());
        response.setBookId(bookCopy.getBook().getId());
        response.setStatus(bookCopy.getStatus());
        return response;
    }

    public UpdatedBookCopyResponse update(UUID id, UpdateBookCopyRequest request) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("BookCopy bulunamadi: " + id));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BusinessException("Book bulunamadi: " + request.getBookId()));

        bookCopy.setBook(book);
        bookCopy.setStatus(request.getStatus());

        bookCopy = bookCopyRepository.save(bookCopy);

        UpdatedBookCopyResponse response = new UpdatedBookCopyResponse();
        response.setId(bookCopy.getId());
        response.setBookId(bookCopy.getBook().getId());
        response.setStatus(bookCopy.getStatus());
        return response;
    }

    public void delete(UUID id) {
        if (!bookCopyRepository.existsById(id)) {
            throw new BusinessException("BookCopy bulunamadi: " + id);
        }
        bookCopyRepository.deleteById(id);
    }
}
