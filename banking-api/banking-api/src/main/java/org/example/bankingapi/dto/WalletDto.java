package org.example.bankingapi.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.example.bankingapi.entity.WalletRecord;

import java.util.ArrayList;
import java.util.List;

public class WalletDto {
    private long cardId;
    private double amount;
    private List<WalletRecordDto> sentTransactions = new ArrayList<>();
    private List<WalletRecordDto> receivedTransactions = new ArrayList<>();

    public WalletDto(long cardId, double amount, List<WalletRecordDto> sentTransactions, List<WalletRecordDto> receivedTransactions) {
        this.cardId = cardId;
        this.amount = amount;
        this.sentTransactions = sentTransactions;
        this.receivedTransactions = receivedTransactions;
    }

    public List<WalletRecordDto> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<WalletRecordDto> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<WalletRecordDto> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<WalletRecordDto> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
