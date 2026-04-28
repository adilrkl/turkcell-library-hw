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

import com.turkcell.spring_library.dto.user.CreateUserRequest;
import com.turkcell.spring_library.dto.user.CreatedUserResponse;
import com.turkcell.spring_library.dto.user.ListUserResponse;
import com.turkcell.spring_library.dto.user.UpdateUserRequest;
import com.turkcell.spring_library.dto.user.UpdatedUserResponse;
import com.turkcell.spring_library.dto.user.UserResponse;
import com.turkcell.spring_library.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserServiceImpl userServiceImpl;

    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public List<ListUserResponse> getAll() {
        return userServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable UUID id) {
        return userServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedUserResponse create(@RequestBody CreateUserRequest request) {
        return userServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedUserResponse update(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        return userServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userServiceImpl.delete(id);
    }
}
