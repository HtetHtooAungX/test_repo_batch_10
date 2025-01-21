package org.example.foodie.dao;

import org.example.foodie.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findById(int id);
}
