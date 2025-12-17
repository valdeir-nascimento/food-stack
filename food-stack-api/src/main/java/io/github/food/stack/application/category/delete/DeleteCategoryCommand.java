package io.github.food.stack.application.category.delete;

public record DeleteCategoryCommand(String id) {

    public static DeleteCategoryCommand with(final String id) {
        return new DeleteCategoryCommand(id);
    }
}
