package org.example.bankingapi.dto;


import java.time.LocalDateTime;

public class WalletRecordDto {
    private long id;
    private long senderWallet;
    private long receiverWallet;
    private double amount;
    private LocalDateTime createdAt;

    public WalletRecordDto(long id, long senderWallet, long receiverWallet, double amount, LocalDateTime createdAt) {
        this.id = id;
        this.senderWallet = senderWallet;
        this.receiverWallet = receiverWallet;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public WalletRecordDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(long senderWallet) {
        this.senderWallet = senderWallet;
    }

    public long getReceiverWallet() {
        return receiverWallet;
    }

    public void setReceiverWallet(long receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
