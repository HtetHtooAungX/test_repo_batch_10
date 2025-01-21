package org.example.foodie.entity;

import jakarta.persistence.Entity;

@Entity
public class OrderedDish extends IdClass{
    private int dishId;
    private String name;
    private double price;
    private int quantity;

    public OrderedDish() {
    }

    public OrderedDish(int dishId, String name, double price, int quantity) {
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
