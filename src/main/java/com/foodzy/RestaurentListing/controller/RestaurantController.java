package com.foodzy.RestaurentListing.controller;

import com.foodzy.RestaurentListing.dto.RestaurantDTO;
import com.foodzy.RestaurentListing.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>>  fetchAllRestaurants(){
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping(value = "/addRestaurant",consumes = "application/json")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO addedRestaurant = restaurantService.addRestaurantToDB(restaurantDTO);
        return new ResponseEntity<>(addedRestaurant,HttpStatus.CREATED);

    }

    @GetMapping(value = "/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer  id){
        return restaurantService.findRestaurantById(id);
    }
}
