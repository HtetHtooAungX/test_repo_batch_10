package org.example.foodie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends IdClass{
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Restaurant> restaurants = new ArrayList<>();

    public Category() {
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setCategory(this);
        this.restaurants.add(restaurant);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
