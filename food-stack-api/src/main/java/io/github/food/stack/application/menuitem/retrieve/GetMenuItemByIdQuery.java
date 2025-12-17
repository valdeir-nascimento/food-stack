package io.github.food.stack.application.menuitem.retrieve;

public record GetMenuItemByIdQuery(String id) {

    public static GetMenuItemByIdQuery with(final String id) {
        return new GetMenuItemByIdQuery(id);
    }
}
