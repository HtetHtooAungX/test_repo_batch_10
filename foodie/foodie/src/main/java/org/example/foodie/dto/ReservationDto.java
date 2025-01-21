package org.example.foodie.dto;

import jakarta.persistence.ManyToOne;
import org.example.foodie.entity.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDto {
    private int numberOfGuests;
    private String email;
    private String phoneNumber;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int tableNumber;
    private String customerName;
    private String restaurantName;

    public ReservationDto(int numberOfGuests, String email, String phoneNumber, LocalDate reservationDate, LocalTime reservationTime, int tableNumber, String customerName, String restaurantName) {
        this.numberOfGuests = numberOfGuests;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.restaurantName = restaurantName;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
