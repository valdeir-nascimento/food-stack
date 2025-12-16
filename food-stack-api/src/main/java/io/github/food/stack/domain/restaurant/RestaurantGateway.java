package io.github.food.stack.domain.restaurant;

public interface RestaurantGateway {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    Restaurant findById(RestaurantID id);

    void deleteById(RestaurantID id);
}
