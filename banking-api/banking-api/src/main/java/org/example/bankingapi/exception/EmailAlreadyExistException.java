package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistException extends ResponseStatusException {
    public EmailAlreadyExistException() {
        super(HttpStatus.BAD_REQUEST, "Email already exist");
    }
}
