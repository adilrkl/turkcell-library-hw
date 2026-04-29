package com.turkcell.spring_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.user.CreateUserRequest;
import com.turkcell.spring_library.dto.user.CreatedUserResponse;
import com.turkcell.spring_library.dto.user.ListUserResponse;
import com.turkcell.spring_library.dto.user.UpdateUserRequest;
import com.turkcell.spring_library.dto.user.UpdatedUserResponse;
import com.turkcell.spring_library.dto.user.UserResponse;
import com.turkcell.spring_library.entity.User;
import com.turkcell.spring_library.exception.BusinessException;
import com.turkcell.spring_library.repository.UserRepository;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ListUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<ListUserResponse> responseList = new ArrayList<>();

        for (User user : users) {
            ListUserResponse response = new ListUserResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setSurname(user.getSurname());
            response.setUserType(user.getUserType());
            responseList.add(response);
        }

        return responseList;
    }

    public UserResponse getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User bulunamadi: " + id));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUserType(user.getUserType());
        response.setAddress(user.getAddress());
        response.setGsm(user.getGsm());
        response.setSchoolNumber(user.getSchoolNumber());
        return response;
    }

    public CreatedUserResponse create(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUserType(request.getUserType());
        user.setAddress(request.getAddress());
        user.setGsm(request.getGsm());
        user.setSchoolNumber(request.getSchoolNumber());

        user = userRepository.save(user);

        CreatedUserResponse response = new CreatedUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUserType(user.getUserType());
        return response;
    }

    public UpdatedUserResponse update(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User bulunamadi: " + id));

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUserType(request.getUserType());
        user.setAddress(request.getAddress());
        user.setGsm(request.getGsm());
        user.setSchoolNumber(request.getSchoolNumber());

        user = userRepository.save(user);

        UpdatedUserResponse response = new UpdatedUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUserType(user.getUserType());
        return response;
    }

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("User bulunamadi: " + id);
        }
        userRepository.deleteById(id);
    }
}
