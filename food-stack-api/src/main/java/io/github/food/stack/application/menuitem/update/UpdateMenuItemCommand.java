package io.github.food.stack.application.menuitem.update;

import java.math.BigDecimal;

public record UpdateMenuItemCommand(
    String id,
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    boolean available
) {
    public static UpdateMenuItemCommand with(
        final String id,
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available) {
        return new UpdateMenuItemCommand(id, name, description, price, imageUrl, available);
    }
}
