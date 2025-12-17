package io.github.food.stack.application.restaurant.retrieve;

import io.github.food.stack.domain.restaurant.Restaurant;

import java.time.Instant;

public record RestaurantOutput(
    String id,
    String name,
    String description,
    String address,
    String phoneNumber,
    String email,
    Instant createdAt,
    Instant updatedAt) {
    public static RestaurantOutput from(final Restaurant restaurant) {
        return new RestaurantOutput(
            restaurant.getId().getValue(),
            restaurant.getName(),
            restaurant.getDescription(),
            restaurant.getAddress(),
            restaurant.getPhoneNumber(),
            restaurant.getEmail(),
            restaurant.getCreatedAt(),
            restaurant.getUpdatedAt());
    }
}
