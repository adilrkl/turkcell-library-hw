package com.turkcell.spring_library.exception;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private List<String> arguments;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(String title, String type, String message, List<String> arguments) {
        super(title, type, message);
        this.arguments = arguments;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}
