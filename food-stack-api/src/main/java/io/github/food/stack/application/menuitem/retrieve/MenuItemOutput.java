package io.github.food.stack.application.menuitem.retrieve;

import io.github.food.stack.domain.menuitem.MenuItem;

import java.math.BigDecimal;
import java.time.Instant;

public record MenuItemOutput(
    String id,
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    boolean available,
    String categoryId,
    Instant createdAt,
    Instant updatedAt
) {
    public static MenuItemOutput from(final MenuItem menuItem) {
        return new MenuItemOutput(
            menuItem.getId().getValue(),
            menuItem.getName(),
            menuItem.getDescription(),
            menuItem.getPrice(),
            menuItem.getImageUrl(),
            menuItem.isAvailable(),
            menuItem.getCategoryId().getValue(),
            menuItem.getCreatedAt(),
            menuItem.getUpdatedAt()
        );
    }
}
