package io.github.food.stack.application.restaurant.update;

import io.github.food.stack.domain.restaurant.Restaurant;

public record UpdateRestaurantOutput(String id) {

    public static UpdateRestaurantOutput from(final Restaurant restaurant) {
        return new UpdateRestaurantOutput(restaurant.getId().getValue());
    }
}
