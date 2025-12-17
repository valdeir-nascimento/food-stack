package io.github.food.stack.application.category.delete;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.category.CategoryGateway;
import io.github.food.stack.domain.category.CategoryID;
import io.github.food.stack.domain.control.Result;

import java.util.Objects;

public class DeleteCategoryUseCase implements CommandHandler<DeleteCategoryCommand, Void> {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Result<Void> handle(final DeleteCategoryCommand command) {
        this.categoryGateway.deleteById(CategoryID.from(command.id()));
        return Result.success(null);
    }
}
