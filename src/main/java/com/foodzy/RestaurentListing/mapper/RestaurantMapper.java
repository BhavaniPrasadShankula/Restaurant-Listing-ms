package com.foodzy.RestaurentListing.mapper;

import com.foodzy.RestaurentListing.dto.RestaurantDTO;
import com.foodzy.RestaurentListing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    //RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
