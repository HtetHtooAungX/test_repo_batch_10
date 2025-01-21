package org.example.foodie.util;

import org.example.foodie.dto.DishDto;
import org.example.foodie.dto.OrderDishDto;
import org.example.foodie.dto.RestaurantDto;
import org.example.foodie.entity.Dish;
import org.example.foodie.entity.OrderedDish;
import org.example.foodie.entity.Restaurant;
import org.springframework.beans.BeanUtils;

public class EntityUtil {

    public static Restaurant toRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(restaurantDto, restaurant);
        return restaurant;
    }

    public static RestaurantDto toRestaurantDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        BeanUtils.copyProperties(restaurant, restaurantDto);
        return restaurantDto;
    }

    public static Dish toDish(DishDto dishDto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto, dish);
        return dish;
    }

    public static DishDto toDishDto(Dish dish) {
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        return dishDto;
    }

    public static OrderedDish toOrderedDish(OrderDishDto orderDishDto) {
        return new OrderedDish(
                orderDishDto.getId(),
                orderDishDto.getName(),
                orderDishDto.getPrice(),
                orderDishDto.getQuantity()
        );
    }
}
