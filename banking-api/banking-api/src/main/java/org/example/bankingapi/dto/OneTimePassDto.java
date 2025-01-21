package org.example.bankingapi.dto;

import org.example.bankingapi.entity.Wallet;

import java.time.LocalDateTime;

public class OneTimePassDto {
    private String otpCode;
    private long walletId;
    private LocalDateTime expiresAt;

    public OneTimePassDto(String otpCode, long walletId, LocalDateTime expiresAt) {
        this.otpCode = otpCode;
        this.walletId = walletId;
        this.expiresAt = expiresAt;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
