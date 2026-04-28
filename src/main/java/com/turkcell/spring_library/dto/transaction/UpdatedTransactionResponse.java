package com.turkcell.spring_library.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.turkcell.spring_library.entity.enums.TransactionStatus;

public class UpdatedTransactionResponse {
    private UUID id;
    private UUID borrowerId;
    private UUID bookCopyId;
    private UUID processedById;
    private LocalDate startDate;
    private LocalDate endDate;
    private TransactionStatus status;
    private LocalDate returnDate;
    private BigDecimal penaltyAmount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public UUID getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(UUID bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public UUID getProcessedById() {
        return processedById;
    }

    public void setProcessedById(UUID processedById) {
        this.processedById = processedById;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
