package io.github.food.stack.application.menuitem.create;

import java.math.BigDecimal;

public record CreateMenuItemCommand(
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    boolean available,
    String categoryId) {
    public static CreateMenuItemCommand with(
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available,
        final String categoryId
    ) {
        return new CreateMenuItemCommand(name, description, price, imageUrl, available, categoryId);
    }
}
