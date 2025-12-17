package io.github.food.stack.application.restaurant.update;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.restaurant.Restaurant;
import io.github.food.stack.domain.restaurant.RestaurantGateway;
import io.github.food.stack.domain.restaurant.RestaurantID;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class UpdateRestaurantUseCase implements CommandHandler<UpdateRestaurantCommand, UpdateRestaurantOutput> {

    private final RestaurantGateway restaurantGateway;

    public UpdateRestaurantUseCase(final RestaurantGateway restaurantGateway) {
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway);
    }

    @Override
    public Result<UpdateRestaurantOutput> handle(final UpdateRestaurantCommand command) {
        final var id = RestaurantID.from(command.id());

        final var maybeRestaurant = this.restaurantGateway.findById(id);
        if (maybeRestaurant.isEmpty()) {
            return Result.failure(NotFoundException.with(Restaurant.class, id));
        }

        final var restaurant = maybeRestaurant.get();
        final var notification = Notification.create();

        restaurant.update(
            command.name(),
            command.description(),
            command.address(),
            command.phoneNumber(),
            command.email());

        restaurant.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var updatedRestaurant = this.restaurantGateway.update(restaurant);

        return Result.success(UpdateRestaurantOutput.from(updatedRestaurant));
    }
}
