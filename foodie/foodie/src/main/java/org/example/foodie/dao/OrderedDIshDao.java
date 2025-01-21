package org.example.foodie.dao;

import org.example.foodie.entity.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDIshDao extends JpaRepository<OrderedDish, Integer> {
}
