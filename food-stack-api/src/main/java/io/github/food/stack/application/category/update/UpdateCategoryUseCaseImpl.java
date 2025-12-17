package io.github.food.stack.application.category.update;

import io.github.food.stack.domain.category.Category;
import io.github.food.stack.domain.category.CategoryGateway;
import io.github.food.stack.domain.category.CategoryID;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public UpdateCategoryUseCaseImpl(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Result<UpdateCategoryOutput> execute(final UpdateCategoryCommand command) {
        final var id = CategoryID.from(command.id());

        final var maybeCategory = this.categoryGateway.findById(id);
        if (maybeCategory.isEmpty()) {
            return Result.failure(NotFoundException.with(Category.class, id));
        }

        final var category = maybeCategory.get();
        final var notification = Notification.create();

        category.update(
                command.name(),
                command.description(),
                command.order());

        category.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var updatedCategory = this.categoryGateway.update(category);

        return Result.success(UpdateCategoryOutput.from(updatedCategory));
    }
}
