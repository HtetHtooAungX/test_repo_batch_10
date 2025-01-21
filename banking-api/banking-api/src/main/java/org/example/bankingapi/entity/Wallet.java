package org.example.bankingapi.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;
    private double amount;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "senderWallet",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WalletRecord> sentTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "receiverWallet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WalletRecord> receivedTransactions = new ArrayList<>();

    public List<WalletRecord> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<WalletRecord> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<WalletRecord> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<WalletRecord> receivedTransactions) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addSentTransaction(WalletRecord walletRecord) {
        walletRecord.setSenderWallet(this);
        this.sentTransactions.add(walletRecord);
    }

    public void addReceivedTransaction(WalletRecord walletRecord) {
        walletRecord.setReceiverWallet(this);
        this.receivedTransactions.add(walletRecord);
    }
}
