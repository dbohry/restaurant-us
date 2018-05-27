package com.danielbohry.restaurantmanagement.controllers;

import com.danielbohry.restaurantmanagement.entities.Restaurant;
import com.danielbohry.restaurantmanagement.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> response = service.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@RequestParam("access_token") String token,
                                          @PathVariable("id") String id) {
        Optional<Restaurant> response = service.get(id);
        return response.map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> save(@RequestParam("access_token") String token,
                                           @RequestBody Restaurant restaurant) {
        Restaurant response = service.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@RequestParam("access_token") String token,
                                             @PathVariable("id") String id,
                                             @RequestBody Restaurant restaurant) {
        Restaurant response = service.update(id, restaurant);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam("access_token") String token,
                                       @PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
