package com.foodzy.RestaurentListing.service;

import com.foodzy.RestaurentListing.dto.RestaurantDTO;
import com.foodzy.RestaurentListing.entity.Restaurant;
import com.foodzy.RestaurentListing.mapper.RestaurantMapper;
import com.foodzy.RestaurentListing.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    RestaurantMapper restaurantMapper;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> allRestaurants = restaurantRepo.findAll();
        return allRestaurants.stream().map(restaurant -> restaurantMapper.mapRestaurantToRestaurantDTO(restaurant)).toList();
     //https://stackoverflow.com/questions/65969919/differences-of-java-16s-stream-tolist-and-stream-collectcollectors-tolist
    }

    public RestaurantDTO addRestaurantToDB(RestaurantDTO restaurantDTO) {
        Restaurant addedRestaurant = restaurantRepo.save(restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO));
        return restaurantMapper.mapRestaurantToRestaurantDTO(addedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(restaurantMapper.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        /*equivalent functional programming style expression for above if condition
        return restaurant.map(value -> new ResponseEntity<>(restaurantMapper.mapRestaurantToRestaurantDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
*/    }
}
