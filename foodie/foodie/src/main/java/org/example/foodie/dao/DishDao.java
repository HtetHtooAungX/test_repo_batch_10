package org.example.foodie.dao;

import org.example.foodie.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishDao extends JpaRepository<Dish, Integer> {
    @Query("""
select d from Dish d where d.restaurant.name = ?1
""")
    List<Dish> findDishByRestaurantName(String restaurantName);
}
