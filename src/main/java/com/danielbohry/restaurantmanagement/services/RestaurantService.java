package com.danielbohry.restaurantmanagement.services;

import com.danielbohry.restaurantmanagement.entities.Restaurant;
import com.danielbohry.restaurantmanagement.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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

    public Restaurant update(String id, Restaurant restaurant) {
        if (repository.existsById(id))
            return repository.save(restaurant);
        else
            throw new ResourceAccessException("This restaurant do not exists");
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
