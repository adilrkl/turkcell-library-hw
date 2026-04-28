package com.turkcell.spring_library.dto.bookcopy;

import java.util.UUID;

import com.turkcell.spring_library.entity.enums.BookCopyStatus;

public class UpdateBookCopyRequest {
    private UUID bookId;
    private BookCopyStatus status;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public BookCopyStatus getStatus() {
        return status;
    }

    public void setStatus(BookCopyStatus status) {
        this.status = status;
    }
}
