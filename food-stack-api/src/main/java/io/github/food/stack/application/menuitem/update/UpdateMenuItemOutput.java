package io.github.food.stack.application.menuitem.update;

import io.github.food.stack.domain.menuitem.MenuItem;

public record UpdateMenuItemOutput(String id) {

    public static UpdateMenuItemOutput from(final MenuItem menuItem) {
        return new UpdateMenuItemOutput(menuItem.getId().getValue());
    }
}
