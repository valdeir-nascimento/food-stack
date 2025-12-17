package io.github.food.stack.application.category.update;

import io.github.food.stack.domain.category.Category;

public record UpdateCategoryOutput(String id) {

    public static UpdateCategoryOutput from(final Category category) {
        return new UpdateCategoryOutput(category.getId().getValue());
    }
}
