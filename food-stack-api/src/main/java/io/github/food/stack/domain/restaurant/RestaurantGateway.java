package io.github.food.stack.domain.restaurant;

import java.util.Optional;

public interface RestaurantGateway {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    Optional<Restaurant> findById(RestaurantID id);

    void deleteById(RestaurantID id);
}
