package io.github.food.stack.application.category.retrieve;

public record GetCategoryByIdQuery(String id) {

    public static GetCategoryByIdQuery with(final String id) {
        return new GetCategoryByIdQuery(id);
    }
}
