package io.github.food.stack.application.category.retrieve;

import io.github.food.stack.domain.category.Category;

import java.time.Instant;

public record CategoryOutput(
    String id,
    String name,
    String description,
    String restaurantId,
    Integer order,
    Instant createdAt,
    Instant updatedAt) {
    public static CategoryOutput from(final Category category) {
        return new CategoryOutput(
            category.getId().getValue(),
            category.getName(),
            category.getDescription(),
            category.getRestaurantId().getValue(),
            category.getOrder(),
            category.getCreatedAt(),
            category.getUpdatedAt());
    }
}
