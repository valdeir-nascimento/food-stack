package io.github.food.stack.application.restaurant.create;

public record CreateRestaurantCommand(
    String name,
    String description,
    String address,
    String phoneNumber,
    String email) {
    public static CreateRestaurantCommand with(
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email) {
        return new CreateRestaurantCommand(name, description, address, phoneNumber, email);
    }
}
