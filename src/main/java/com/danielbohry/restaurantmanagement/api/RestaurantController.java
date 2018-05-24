package com.danielbohry.restaurantmanagement.api;

import com.danielbohry.restaurantmanagement.business.RestaurantService;
import com.danielbohry.restaurantmanagement.infrastructure.restaurant.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantEntity>> getAll() {
        List<RestaurantEntity> response = service.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantEntity> get(@PathVariable("id") Long id) {
        RestaurantEntity response = service.get(id);
        return ResponseEntity.ok().body(response);
    }

}
