package com.danielbohry.restaurantmanagement.services;

import com.danielbohry.restaurantmanagement.entities.Restaurant;
import com.danielbohry.restaurantmanagement.repositories.RestaurantRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {

    private static final String ID = "id";
    private static final String RESTAURANT_NAME = "my favorite restaurant";
    private RestaurantService service;
    private RestaurantRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        service = new RestaurantService(repository);
    }

    @Test
    public void shouldReturnListOfRestaurants() {
        when(repository.findAll()).thenReturn(Collections.singletonList(mockRestaurantObject()));

        List<Restaurant> response = service.getAll();
        List<Restaurant> expected = Collections.singletonList(mockRestaurantObject());

        assertThat(response, is(expected));
    }

    @Test
    public void shouldReturnRestaurantById() {
        when(repository.findById(ID)).thenReturn(Optional.of(mockRestaurantObject()));

        Optional<Restaurant> response = service.get(ID);
        Optional<Restaurant> expected = Optional.of(mockRestaurantObject());

        assertThat(response, is(expected));
    }

    @Test
    public void shouldReturnEmptyIfTryToRequestAnInexistentRestaurantById() {
        when(repository.findById(ID)).thenReturn(Optional.empty());

        Optional<Restaurant> response = service.get(ID);
        Optional<Restaurant> expected = Optional.empty();

        assertThat(response, is(expected));
    }

    @Test
    public void shouldUpdateARestaurant() {
        when(repository.existsById(ID)).thenReturn(Boolean.TRUE);

        service.update(ID, mockRestaurantObject());
        verify(repository).save(mockRestaurantObject());
    }

    @Test
    public void shouldReturnExceptionIfTryToUpdateAnInexistentRestaurant() {
        when(repository.existsById(ID)).thenReturn(Boolean.FALSE);

        thrown.expect(ResourceAccessException.class);
        thrown.expectMessage("This restaurant do not exists");

        service.update(ID, mockRestaurantObject());
    }

    @Test
    public void shouldDeleteARestaurant() {
        service.delete(ID);
        verify(repository).deleteById(ID);
    }

    private Restaurant mockRestaurantObject() {
        return new Restaurant(ID, RESTAURANT_NAME);
    }

}
