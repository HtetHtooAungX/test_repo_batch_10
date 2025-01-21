package org.example.foodie.dao;

import org.example.foodie.entity.IdClass;
import org.example.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByName(String restaurantName);
}
