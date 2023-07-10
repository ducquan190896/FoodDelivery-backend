package com.fooddelivery.backend.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fooddelivery.backend.Models.Basket;
import com.fooddelivery.backend.Models.Customer;
import com.fooddelivery.backend.Models.Restaurant;
import  com.fooddelivery.backend.Models.Users;

@Repository
public interface BasketRepos extends JpaRepository<Basket, Long> {
    Optional<Basket> findByCustomerAndRestaurant(Customer customer, Restaurant restaurant);
}
