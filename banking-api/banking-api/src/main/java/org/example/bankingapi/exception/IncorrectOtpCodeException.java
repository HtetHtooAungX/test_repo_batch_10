package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectOtpCodeException extends ResponseStatusException {
    public IncorrectOtpCodeException() {
        super(HttpStatus.BAD_REQUEST, "Incorrect OTP Code");
    }
}
