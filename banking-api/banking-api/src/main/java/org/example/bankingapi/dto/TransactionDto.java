package org.example.bankingapi.dto;

public class TransactionDto {
    private long senderCardId;
    private String otpCode;
    private double amount;

    public TransactionDto(long senderCardId, String otpCode, double amount) {
        this.senderCardId = senderCardId;
        this.otpCode = otpCode;
        this.amount = amount;
    }

    public TransactionDto() {
    }

    public long getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(long senderCardId) {
        this.senderCardId = senderCardId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
