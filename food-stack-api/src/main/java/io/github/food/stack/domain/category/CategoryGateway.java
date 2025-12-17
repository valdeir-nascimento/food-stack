package io.github.food.stack.domain.category;

public interface CategoryGateway {

    Category create(Category category);

    Category update(Category category);

    Category findById(CategoryID id);

    void deleteById(CategoryID id);
}
