package com.danielbohry.restaurantmanagement;

import com.danielbohry.restaurantmanagement.entities.User;
import com.danielbohry.restaurantmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.Arrays;

@SpringBootApplication
@EnableOAuth2Sso
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
        if (repo.count() == 0)
            repo.save(new User(
                    "admin",
                    "admin",
                    new String[]{"USER", "ADMIN"}));

        builder.userDetailsService(username -> new CostomUserDetails(repo.findByUsername(username)));
    }

}
