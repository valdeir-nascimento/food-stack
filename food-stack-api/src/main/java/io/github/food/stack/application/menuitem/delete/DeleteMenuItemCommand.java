package io.github.food.stack.application.menuitem.delete;

public record DeleteMenuItemCommand(String id) {

    public static DeleteMenuItemCommand with(final String id) {
        return new DeleteMenuItemCommand(id);
    }
}
