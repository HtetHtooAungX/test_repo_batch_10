package org.example.foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant extends IdClass {
    @Column(unique = true, nullable = false)
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String imageUrl;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "review_for_restaurant")
    private List<Remark> remarks = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Reservation> reservations = new ArrayList<>();

    public Restaurant(String name, String address, String phone, String email, LocalTime openTime, LocalTime closeTime, String imageUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageUrl = imageUrl;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }

    public void addDish(Dish dish) {
        dish.setRestaurant(this);
        this.dishes.add(dish);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        reservation.setRestaurant(this);
        this.reservations.add(reservation);
    }
}
