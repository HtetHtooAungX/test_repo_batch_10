package org.example.foodie.dao;

import org.example.foodie.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);

    boolean existsByUsername(String username);
}
