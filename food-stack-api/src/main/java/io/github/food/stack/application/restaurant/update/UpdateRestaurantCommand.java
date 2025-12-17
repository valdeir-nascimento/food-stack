package io.github.food.stack.application.restaurant.update;

public record UpdateRestaurantCommand(
    String id,
    String name,
    String description,
    String address,
    String phoneNumber,
    String email) {
    public static UpdateRestaurantCommand with(
        final String id,
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email) {
        return new UpdateRestaurantCommand(id, name, description, address, phoneNumber, email);
    }
}
