package com.danielbohry.restaurantmanagement.services;

import com.danielbohry.restaurantmanagement.entities.User;
import com.danielbohry.restaurantmanagement.repositories.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final List<SimpleGrantedAuthority> ROLES =
            Collections.singletonList(new SimpleGrantedAuthority("ROLE"));
    private UserService service;
    private UserRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    public void shouldReturnAnUser() {
        when(repository.findByUsername(USERNAME)).thenReturn(Optional.of(new User(USERNAME, PASSWORD)));

        UserDetails response = service.loadUserByUsername(USERNAME);
        UserDetails expected = new org.springframework.security.core.userdetails.User(USERNAME, PASSWORD, ROLES);

        assertThat(response, is(expected));
    }

    @Test
    public void shouldReturnExceptionIfUserNotFound() {
        when(repository.findByUsername(USERNAME)).thenReturn(Optional.empty());

        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage("Invalid username or password.");

        service.loadUserByUsername(USERNAME);
    }

}
