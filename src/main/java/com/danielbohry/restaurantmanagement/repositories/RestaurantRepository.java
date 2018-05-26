package com.danielbohry.restaurantmanagement.repositories;

import com.danielbohry.restaurantmanagement.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Optional<Restaurant> findById(String id);

}
