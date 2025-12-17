package io.github.food.stack.application.restaurant.create;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.restaurant.Restaurant;
import io.github.food.stack.domain.restaurant.RestaurantGateway;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final RestaurantGateway restaurantGateway;

    public CreateRestaurantUseCaseImpl(final RestaurantGateway restaurantGateway) {
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway);
    }

    @Override
    public Result<CreateRestaurantOutput> execute(final CreateRestaurantCommand command) {
        final var notification = Notification.create();

        final var restaurant = Restaurant.newRestaurant(
                command.name(),
                command.description(),
                command.address(),
                command.phoneNumber(),
                command.email());

        restaurant.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedRestaurant = this.restaurantGateway.create(restaurant);

        return Result.success(CreateRestaurantOutput.from(savedRestaurant));
    }
}
