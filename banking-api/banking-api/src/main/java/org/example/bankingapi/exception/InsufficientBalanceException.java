package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientBalanceException extends ResponseStatusException {
    public InsufficientBalanceException() {
        super(HttpStatus.BAD_REQUEST, "Insufficient Balance");
    }
}
