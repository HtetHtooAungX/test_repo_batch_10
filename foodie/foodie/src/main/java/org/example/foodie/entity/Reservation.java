package org.example.foodie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberOfGuests;
    private String email;
    private String phoneNumber;
    @Future(message = "Reservation Date Should be future")
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int tableNumber;
    private String customerName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Restaurant restaurant;
    @JoinColumn(name = "reservation_id_fk")
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderedDish> orderedDishes = new ArrayList<>();
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.PERSIST)
    private PaymentRecord paymentRecord;

    public Reservation() {
    }

    public Reservation(int numberOfGuests, String email, String phoneNumber, LocalDate reservationDate, LocalTime reservationTime, int tableNumber, String customerName) {
        this.numberOfGuests = numberOfGuests;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public PaymentRecord getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public void addOrderedDish(OrderedDish orderedDish) {
        orderedDishes.add(orderedDish);
    }
}
