package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EmailAlreadyExistException.class, IncorrectOtpCodeException.class, InsufficientBalanceException.class, UserNameAlreadyExistException.class, WalletNotFoundException.class})
    public ResponseEntity<ApiError> handleError(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
}
