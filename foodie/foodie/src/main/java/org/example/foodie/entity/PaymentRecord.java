package org.example.foodie.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class PaymentRecord extends IdClass{
    private int cardId;
    private double amount;
    private LocalDateTime recordAt;
    @OneToOne
    private Reservation reservation;

    public PaymentRecord() {
    }

    public PaymentRecord(int cardId, double amount, LocalDateTime recordAt, Reservation reservation) {
        this.cardId = cardId;
        this.amount = amount;
        this.recordAt = recordAt;
        this.reservation = reservation;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getRecordAt() {
        return recordAt;
    }

    public void setRecordAt(LocalDateTime recordAt) {
        this.recordAt = recordAt;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
