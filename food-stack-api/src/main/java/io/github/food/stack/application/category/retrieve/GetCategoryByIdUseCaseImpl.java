package io.github.food.stack.application.category.retrieve;

import io.github.food.stack.domain.category.Category;
import io.github.food.stack.domain.category.CategoryGateway;
import io.github.food.stack.domain.category.CategoryID;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;

import java.util.Objects;

public class GetCategoryByIdUseCaseImpl implements GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public GetCategoryByIdUseCaseImpl(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Result<CategoryOutput> execute(final GetCategoryByIdQuery query) {
        final var id = CategoryID.from(query.id());

        return this.categoryGateway.findById(id)
                .map(CategoryOutput::from)
                .map(Result::success)
                .orElseGet(() -> Result.failure(NotFoundException.with(Category.class, id)));
    }
}
