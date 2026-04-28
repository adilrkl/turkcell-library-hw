package com.turkcell.spring_library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.spring_library.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
