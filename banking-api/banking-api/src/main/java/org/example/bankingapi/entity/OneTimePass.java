package org.example.bankingapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OneTimePass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int otpId;
    private String otpCode;
    @ManyToOne
    private Wallet wallet;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;

    public OneTimePass(String otpCode, Wallet wallet, LocalDateTime createdAt, LocalDateTime expiresAt, boolean isUsed) {
        this.otpCode = otpCode;
        this.wallet = wallet;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isUsed = isUsed;
    }

    public OneTimePass() {

    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getOtpId() {
        return otpId;
    }

    public void setOtpId(int otpId) {
        this.otpId = otpId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
