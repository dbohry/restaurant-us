package com.danielbohry.restaurantmanagement.business;

import com.danielbohry.restaurantmanagement.infrastructure.restaurant.RestaurantEntity;
import com.danielbohry.restaurantmanagement.infrastructure.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<RestaurantEntity> getAll() {
        return Collections.singletonList(new RestaurantEntity(1L, "test"));
    }

    public RestaurantEntity get(Long id) {
        return new RestaurantEntity(id, "test");
    }

}
