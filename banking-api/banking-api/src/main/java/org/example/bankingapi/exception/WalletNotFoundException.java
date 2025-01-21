package org.example.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WalletNotFoundException extends ResponseStatusException {
    public WalletNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Wallet not found");
    }
}
