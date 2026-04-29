package com.turkcell.spring_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.author.AuthorResponse;
import com.turkcell.spring_library.dto.author.CreateAuthorRequest;
import com.turkcell.spring_library.dto.author.CreatedAuthorResponse;
import com.turkcell.spring_library.dto.author.ListAuthorResponse;
import com.turkcell.spring_library.dto.author.UpdateAuthorRequest;
import com.turkcell.spring_library.dto.author.UpdatedAuthorResponse;
import com.turkcell.spring_library.entity.Author;
import com.turkcell.spring_library.exception.BusinessException;
import com.turkcell.spring_library.repository.AuthorRepository;

@Service
public class AuthorServiceImpl {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<ListAuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<ListAuthorResponse> responseList = new ArrayList<>();

        for (Author author : authors) {
            ListAuthorResponse response = new ListAuthorResponse();
            response.setId(author.getId());
            response.setName(author.getName());
            response.setSurname(author.getSurname());
            responseList.add(response);
        }

        return responseList;
    }

    public AuthorResponse getById(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Author bulunamadi: " + id));

        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setSurname(author.getSurname());
        return response;
    }

    public CreatedAuthorResponse create(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setSurname(request.getSurname());

        author = authorRepository.save(author);

        CreatedAuthorResponse response = new CreatedAuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setSurname(author.getSurname());
        return response;
    }

    public UpdatedAuthorResponse update(UUID id, UpdateAuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Author bulunamadi: " + id));

        author.setName(request.getName());
        author.setSurname(request.getSurname());
        author = authorRepository.save(author);

        UpdatedAuthorResponse response = new UpdatedAuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setSurname(author.getSurname());
        return response;
    }

    public void delete(UUID id) {
        if (!authorRepository.existsById(id)) {
            throw new BusinessException("Author bulunamadi: " + id);
        }
        authorRepository.deleteById(id);
    }
}
