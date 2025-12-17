package io.github.food.stack.application.category.create;

public record CreateCategoryCommand(
    String name,
    String description,
    String restaurantId,
    Integer order) {
    public static CreateCategoryCommand with(
        final String name,
        final String description,
        final String restaurantId,
        final Integer order) {
        return new CreateCategoryCommand(name, description, restaurantId, order);
    }
}
