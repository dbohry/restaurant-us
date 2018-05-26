package com.danielbohry.restaurantmanagement.business;

import com.danielbohry.restaurantmanagement.entities.restaurant.Restaurant;
import com.danielbohry.restaurantmanagement.entities.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Optional<Restaurant> get(String id) {
        return repository.findById(id);
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
