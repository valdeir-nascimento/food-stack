package io.github.food.stack.application.restaurant.create;

import io.github.food.stack.domain.restaurant.Restaurant;

public record CreateRestaurantOutput(String id) {

    public static CreateRestaurantOutput from(final Restaurant restaurant) {
        return new CreateRestaurantOutput(restaurant.getId().getValue());
    }
}
