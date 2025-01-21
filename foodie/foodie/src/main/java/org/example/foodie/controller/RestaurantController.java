package org.example.foodie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.foodie.dto.*;
import org.example.foodie.service.BankApiService;
import org.example.foodie.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodie")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final BankApiService bankApiService;

    public RestaurantController(RestaurantService restaurantService, BankApiService bankApiService) {
        this.restaurantService = restaurantService;
        this.bankApiService = bankApiService;
    }

    @GetMapping("/restaurant/{name}")
    public RestaurantDto getRestaurantByName(@PathVariable String name){
        return restaurantService.findRestaurantByName(name);
    }

    @GetMapping("/restaurants")
    public List<RestaurantDto> listAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{restaurantName}")
    public List<DishDto> listAllDishesByRestaurantName(@PathVariable String restaurantName) {
        return restaurantService.listDishesByRestaurantName(restaurantName);
    }

    @GetMapping("/dishes")
    public List<DishDto> listAllDishes() {
        return restaurantService.getAllDishes();
    }

//    @PostMapping("/reservation")
//    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto){
//        String response = restaurantService.saveReservation(reservationDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDtoV2 reservationDtoV2){
        String response = restaurantService.saveReservationV2(reservationDtoV2);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> confirmPayment(@RequestBody PaymentDto paymentDto) throws JsonProcessingException {
        String response = restaurantService.confirmPayment(paymentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/resend/{cardId}")
    public ResponseEntity<Void> resendOtpCode(@PathVariable int cardId) {
        bankApiService.generateOtp(cardId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
