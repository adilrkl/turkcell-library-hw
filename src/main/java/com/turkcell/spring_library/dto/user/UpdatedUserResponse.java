package com.turkcell.spring_library.dto.user;

import java.util.UUID;

import com.turkcell.spring_library.entity.enums.UserType;

public class UpdatedUserResponse {
    private UUID id;
    private String name;
    private String surname;
    private UserType userType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
