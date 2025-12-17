package io.github.food.stack.domain.category;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);

    Category update(Category category);

    Optional<Category> findById(CategoryID id);

    void deleteById(CategoryID id);
}
