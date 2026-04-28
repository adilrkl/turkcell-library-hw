package com.turkcell.spring_library.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.turkcell.spring_library.entity.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 50)
    private UserType userType;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "gsm", length = 20)
    private String gsm;

    @Column(name = "school_number", length = 50)
    private String schoolNumber;

    @OneToMany(mappedBy = "borrower")
    private List<Transaction> borrowedTransactions;

    @OneToMany(mappedBy = "processedBy")
    private List<Transaction> processedTransactions;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public List<Transaction> getBorrowedTransactions() {
        return borrowedTransactions;
    }

    public void setBorrowedTransactions(List<Transaction> borrowedTransactions) {
        this.borrowedTransactions = borrowedTransactions;
    }

    public List<Transaction> getProcessedTransactions() {
        return processedTransactions;
    }

    public void setProcessedTransactions(List<Transaction> processedTransactions) {
        this.processedTransactions = processedTransactions;
    }
}
