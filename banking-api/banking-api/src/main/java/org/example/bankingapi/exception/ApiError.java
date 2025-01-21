package org.example.bankingapi.exception;

import java.time.LocalDateTime;

public record ApiError(
        int statusCode,
        String message,
        LocalDateTime timestamp
) {
}
