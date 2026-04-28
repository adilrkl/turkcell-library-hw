package com.turkcell.spring_library.dto.transaction;

import java.time.LocalDate;
import java.util.UUID;

public class CreateTransactionRequest {
    private UUID borrowerId;
    private UUID bookCopyId;
    private UUID processedById;
    private LocalDate startDate;
    private LocalDate endDate;

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
}
