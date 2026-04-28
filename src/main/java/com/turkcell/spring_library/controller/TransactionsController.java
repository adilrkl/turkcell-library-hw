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

import com.turkcell.spring_library.dto.transaction.CreateTransactionRequest;
import com.turkcell.spring_library.dto.transaction.CreatedTransactionResponse;
import com.turkcell.spring_library.dto.transaction.ListTransactionResponse;
import com.turkcell.spring_library.dto.transaction.TransactionResponse;
import com.turkcell.spring_library.dto.transaction.UpdateTransactionRequest;
import com.turkcell.spring_library.dto.transaction.UpdatedTransactionResponse;
import com.turkcell.spring_library.service.TransactionServiceImpl;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
    private final TransactionServiceImpl transactionServiceImpl;

    public TransactionsController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @GetMapping
    public List<ListTransactionResponse> getAll() {
        return transactionServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable UUID id) {
        return transactionServiceImpl.getById(id);
    }

    @PostMapping
    public CreatedTransactionResponse create(@RequestBody CreateTransactionRequest request) {
        return transactionServiceImpl.create(request);
    }

    @PutMapping("/{id}")
    public UpdatedTransactionResponse update(@PathVariable UUID id, @RequestBody UpdateTransactionRequest request) {
        return transactionServiceImpl.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        transactionServiceImpl.delete(id);
    }
}
