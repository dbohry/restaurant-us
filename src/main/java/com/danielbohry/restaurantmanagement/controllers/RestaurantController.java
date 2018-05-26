package com.danielbohry.restaurantmanagement.controllers;

import com.danielbohry.restaurantmanagement.services.RestaurantService;
import com.danielbohry.restaurantmanagement.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> response = service.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("management/restaurant/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable("id") String id) {
        Optional<Restaurant> response = service.get(id);
        return response.map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("management/restaurant")
    public ResponseEntity<Restaurant> save(@PathParam("access_token") String token,
                                           @RequestBody Restaurant restaurant) {
        Restaurant response = service.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("management/restaurant/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable("id") String id,
                                             @RequestBody Restaurant restaurant) {
        Restaurant response = service.update(id, restaurant);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("management/restaurant/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
