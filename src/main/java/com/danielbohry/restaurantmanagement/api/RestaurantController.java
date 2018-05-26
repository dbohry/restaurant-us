package com.danielbohry.restaurantmanagement.api;

import com.danielbohry.restaurantmanagement.business.RestaurantService;
import com.danielbohry.restaurantmanagement.entities.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
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

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> get(@PathVariable("id") String id) {
        Optional<Restaurant> response = service.get(id);
        return response.map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> save(@RequestBody Restaurant restaurant) {
        Restaurant response = service.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) {
        Restaurant response = service.update(restaurant);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
