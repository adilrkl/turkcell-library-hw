package com.turkcell.spring_library.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_library.dto.transaction.CreateTransactionRequest;
import com.turkcell.spring_library.dto.transaction.CreatedTransactionResponse;
import com.turkcell.spring_library.dto.transaction.ListTransactionResponse;
import com.turkcell.spring_library.dto.transaction.TransactionResponse;
import com.turkcell.spring_library.dto.transaction.UpdateTransactionRequest;
import com.turkcell.spring_library.dto.transaction.UpdatedTransactionResponse;
import com.turkcell.spring_library.entity.BookCopy;
import com.turkcell.spring_library.entity.Transaction;
import com.turkcell.spring_library.entity.User;
import com.turkcell.spring_library.entity.enums.TransactionStatus;
import com.turkcell.spring_library.repository.BookCopyRepository;
import com.turkcell.spring_library.repository.TransactionRepository;
import com.turkcell.spring_library.repository.UserRepository;

@Service
public class TransactionServiceImpl {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BookCopyRepository bookCopyRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserRepository userRepository,
                                  BookCopyRepository bookCopyRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<ListTransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<ListTransactionResponse> responseList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            ListTransactionResponse response = new ListTransactionResponse();
            response.setId(transaction.getId());
            if (transaction.getBorrower() != null) {
                response.setBorrowerName(transaction.getBorrower().getName() + " " + transaction.getBorrower().getSurname());
            }
            if (transaction.getBookCopy() != null && transaction.getBookCopy().getBook() != null) {
                response.setBookName(transaction.getBookCopy().getBook().getName());
            }
            response.setStartDate(transaction.getStartDate());
            response.setEndDate(transaction.getEndDate());
            response.setReturnDate(transaction.getReturnDate());
            response.setStatus(transaction.getStatus());
            responseList.add(response);
        }

        return responseList;
    }

    public TransactionResponse getById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction bulunamadi: " + id));

        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        if (transaction.getBorrower() != null) {
            response.setBorrowerId(transaction.getBorrower().getId());
            response.setBorrowerName(transaction.getBorrower().getName() + " " + transaction.getBorrower().getSurname());
        }
        if (transaction.getBookCopy() != null) {
            response.setBookCopyId(transaction.getBookCopy().getId());
            if (transaction.getBookCopy().getBook() != null) {
                response.setBookName(transaction.getBookCopy().getBook().getName());
            }
        }
        if (transaction.getProcessedBy() != null) {
            response.setProcessedById(transaction.getProcessedBy().getId());
            response.setProcessedByName(transaction.getProcessedBy().getName() + " " + transaction.getProcessedBy().getSurname());
        }
        response.setStartDate(transaction.getStartDate());
        response.setEndDate(transaction.getEndDate());
        response.setStatus(transaction.getStatus());
        response.setReturnDate(transaction.getReturnDate());
        response.setPenaltyAmount(transaction.getPenaltyAmount());
        return response;
    }

    public CreatedTransactionResponse create(CreateTransactionRequest request) {
        User borrower = userRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower bulunamadi: " + request.getBorrowerId()));
        BookCopy bookCopy = bookCopyRepository.findById(request.getBookCopyId())
                .orElseThrow(() -> new RuntimeException("BookCopy bulunamadi: " + request.getBookCopyId()));
        User processedBy = userRepository.findById(request.getProcessedById())
                .orElseThrow(() -> new RuntimeException("ProcessedBy bulunamadi: " + request.getProcessedById()));

        Transaction transaction = new Transaction();
        transaction.setBorrower(borrower);
        transaction.setBookCopy(bookCopy);
        transaction.setProcessedBy(processedBy);
        transaction.setStartDate(request.getStartDate());
        transaction.setEndDate(request.getEndDate());
        transaction.setStatus(TransactionStatus.GOOD);
        transaction.setPenaltyAmount(BigDecimal.ZERO);

        transaction = transactionRepository.save(transaction);

        CreatedTransactionResponse response = new CreatedTransactionResponse();
        response.setId(transaction.getId());
        response.setBorrowerId(transaction.getBorrower().getId());
        response.setBookCopyId(transaction.getBookCopy().getId());
        response.setProcessedById(transaction.getProcessedBy().getId());
        response.setStartDate(transaction.getStartDate());
        response.setEndDate(transaction.getEndDate());
        return response;
    }

    public UpdatedTransactionResponse update(UUID id, UpdateTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction bulunamadi: " + id));

        User borrower = userRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower bulunamadi: " + request.getBorrowerId()));
        BookCopy bookCopy = bookCopyRepository.findById(request.getBookCopyId())
                .orElseThrow(() -> new RuntimeException("BookCopy bulunamadi: " + request.getBookCopyId()));
        User processedBy = userRepository.findById(request.getProcessedById())
                .orElseThrow(() -> new RuntimeException("ProcessedBy bulunamadi: " + request.getProcessedById()));

        transaction.setBorrower(borrower);
        transaction.setBookCopy(bookCopy);
        transaction.setProcessedBy(processedBy);
        transaction.setStartDate(request.getStartDate());
        transaction.setEndDate(request.getEndDate());
        transaction.setStatus(request.getStatus());
        transaction.setReturnDate(request.getReturnDate());
        transaction.setPenaltyAmount(request.getPenaltyAmount());

        transaction = transactionRepository.save(transaction);

        UpdatedTransactionResponse response = new UpdatedTransactionResponse();
        response.setId(transaction.getId());
        response.setBorrowerId(transaction.getBorrower().getId());
        response.setBookCopyId(transaction.getBookCopy().getId());
        response.setProcessedById(transaction.getProcessedBy().getId());
        response.setStartDate(transaction.getStartDate());
        response.setEndDate(transaction.getEndDate());
        response.setStatus(transaction.getStatus());
        response.setReturnDate(transaction.getReturnDate());
        response.setPenaltyAmount(transaction.getPenaltyAmount());
        return response;
    }

    public void delete(UUID id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction bulunamadi: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
