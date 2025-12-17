package io.github.food.stack.application.menuitem.create;

import io.github.food.stack.domain.menuitem.MenuItem;

public record CreateMenuItemOutput(String id) {

    public static CreateMenuItemOutput from(final MenuItem menuItem) {
        return new CreateMenuItemOutput(menuItem.getId().getValue());
    }
}
