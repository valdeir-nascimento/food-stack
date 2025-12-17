package io.github.food.stack.application.category.create;

import io.github.food.stack.domain.category.Category;

public record CreateCategoryOutput(String id) {

    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId().getValue());
    }
}
