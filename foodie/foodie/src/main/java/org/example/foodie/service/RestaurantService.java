package org.example.foodie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.example.foodie.dao.DishDao;
import org.example.foodie.dao.ReservationDao;
import org.example.foodie.dao.RestaurantDao;
import org.example.foodie.dto.*;
import org.example.foodie.entity.OrderedDish;
import org.example.foodie.entity.PaymentRecord;
import org.example.foodie.entity.Reservation;
import org.example.foodie.entity.Restaurant;
import org.example.foodie.util.EntityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    private final DishDao dishDao;
    private final ReservationDao reservationDao;
    private final BankApiService bankApiService;

    public RestaurantService(RestaurantDao restaurantDao, DishDao dishDao, ReservationDao reservationDao, BankApiService bankApiService) {
        this.restaurantDao = restaurantDao;
        this.dishDao = dishDao;
        this.reservationDao = reservationDao;
        this.bankApiService = bankApiService;
    }

    public RestaurantDto findRestaurantByName(String name) {
        return restaurantDao.findByName(name)
                .map(EntityUtil::toRestaurantDto)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
    }

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantDao.findAll().stream()
                .map(EntityUtil::toRestaurantDto)
                .toList();
    }

    public List<DishDto> listDishesByRestaurantName(String restaurantName) {
        return dishDao.findDishByRestaurantName(restaurantName)
                .stream()
                .map(EntityUtil::toDishDto)
                .toList();
    }

    public List<DishDto> getAllDishes() {
        return dishDao.findAll().
                stream()
                .map(EntityUtil::toDishDto)
                .toList();
    }

    @Transactional
    public String saveReservationV2(ReservationDtoV2 reservationDtoV2) {
        try {
            bankApiService.generateOtp(reservationDtoV2.getCardId());
        } catch (RuntimeException e) {
            return "";
        }
        Restaurant restaurant = getRestaurant(reservationDtoV2);
        List<OrderedDish> orderDishs = reservationDtoV2.getOrderDishes()
                .stream().map(EntityUtil::toOrderedDish)
                .collect(Collectors.toUnmodifiableList());
        Reservation reservation = toReservation(reservationDtoV2);

        if (Objects.nonNull(reservation)) {
            restaurant.setId(restaurant.getId());
            orderDishs.forEach(reservation::addOrderedDish);
            restaurant.addReservation(reservation);
//            restaurantDao.save(restaurant);
            return String.valueOf(reservationDao.save(reservation).getId());
        }
        else {
            throw new EntityNotFoundException("Restaurant not found");
        }
    }

    public String saveReservation(ReservationDto reservationDto) {
        Restaurant restaurant = getRestaurant(reservationDto);
        Reservation reservation = toReservation(reservationDto);

        if (Objects.nonNull(restaurant)){
            restaurant.setId(restaurant.getId());
            restaurant.addReservation(reservation);
            restaurantDao.save(restaurant);
            return "reservation successfully saved";
        } else {
            throw new EntityNotFoundException("Restaurant not found");
        }
    }

    @Transactional
    public String confirmPayment(PaymentDto paymentDto) {
        Reservation reservation = reservationDao.findById(paymentDto.getReservationId()).orElse(null);
        if (Objects.nonNull(reservation)) {
            TransactionDto transactionDto = new TransactionDto(
                    paymentDto.getCardId(),
                    paymentDto.getOtpCode(),
                    paymentDto.getAmount()
            );
            try {
                bankApiService.doTransaction(transactionDto);
            } catch (JsonProcessingException | RuntimeException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
            reservation.setId(reservation.getId());
            PaymentRecord paymentRecord = new PaymentRecord(
                    paymentDto.getCardId(),
                    paymentDto.getAmount(),
                    LocalDateTime.now(),
                    reservation
            );
            reservation.setPaymentRecord(paymentRecord);
            reservationDao.save(reservation);
            return "payment successfully saved";
        } else {
            throw new EntityNotFoundException("Reservation not found");
        }
    }

    private static Reservation toReservation(ReservationDtoV2 reservationDto) {
        Reservation reservation = new Reservation(reservationDto.getNumberOfGuests(),
                reservationDto.getEmail(),
                reservationDto.getPhoneNumber(),
                reservationDto.getReservationDate(),
                reservationDto.getReservationTime(),
                reservationDto.getTableNumber(),
                reservationDto.getCustomerName());
        return reservation;
    }

    private static Reservation toReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation(reservationDto.getNumberOfGuests(),
                reservationDto.getEmail(),
                reservationDto.getPhoneNumber(),
                reservationDto.getReservationDate(),
                reservationDto.getReservationTime(),
                reservationDto.getTableNumber(),
                reservationDto.getCustomerName());
        return reservation;
    }

    private Restaurant getRestaurant(ReservationDto reservationDto) {
        return restaurantDao.findByName(reservationDto.getRestaurantName()).get();
    }

    private Restaurant getRestaurant(ReservationDtoV2 reservationDto) {
        return restaurantDao.findByName(reservationDto.getRestaurantName()).get();
    }
}
