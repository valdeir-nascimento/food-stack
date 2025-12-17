package io.github.food.stack.application.category.update;

public record UpdateCategoryCommand(
    String id,
    String name,
    String description,
    Integer order) {
    public static UpdateCategoryCommand with(
        final String id,
        final String name,
        final String description,
        final Integer order) {
        return new UpdateCategoryCommand(id, name, description, order);
    }
}
