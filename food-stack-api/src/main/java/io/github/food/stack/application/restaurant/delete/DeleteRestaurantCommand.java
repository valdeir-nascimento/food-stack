package io.github.food.stack.application.restaurant.delete;

public record DeleteRestaurantCommand(String id) {

    public static DeleteRestaurantCommand with(final String id) {
        return new DeleteRestaurantCommand(id);
    }
}
