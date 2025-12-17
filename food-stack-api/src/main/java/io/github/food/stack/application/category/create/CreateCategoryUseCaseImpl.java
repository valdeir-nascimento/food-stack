package io.github.food.stack.application.category.create;

import io.github.food.stack.domain.category.Category;
import io.github.food.stack.domain.category.CategoryGateway;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.restaurant.RestaurantID;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCaseImpl(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Result<CreateCategoryOutput> execute(final CreateCategoryCommand command) {
        final var notification = Notification.create();

        final var category = Category.newCategory(
                command.name(),
                command.description(),
                RestaurantID.from(command.restaurantId()),
                command.order());

        category.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedCategory = this.categoryGateway.create(category);

        return Result.success(CreateCategoryOutput.from(savedCategory));
    }
}
