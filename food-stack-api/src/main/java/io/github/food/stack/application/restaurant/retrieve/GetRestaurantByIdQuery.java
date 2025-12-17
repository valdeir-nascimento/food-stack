package io.github.food.stack.application.restaurant.retrieve;

public record GetRestaurantByIdQuery(String id) {

    public static GetRestaurantByIdQuery with(final String id) {
        return new GetRestaurantByIdQuery(id);
    }
}
