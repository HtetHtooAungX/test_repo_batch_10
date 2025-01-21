package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNameAlreadyExistException extends ResponseStatusException {
    public UserNameAlreadyExistException() {
        super(HttpStatus.BAD_REQUEST, "Username already exists");
    }
}
