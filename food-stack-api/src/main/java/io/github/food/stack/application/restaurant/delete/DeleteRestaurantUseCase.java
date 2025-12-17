package io.github.food.stack.application.restaurant.delete;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.restaurant.RestaurantGateway;
import io.github.food.stack.domain.restaurant.RestaurantID;

import java.util.Objects;

public class DeleteRestaurantUseCase implements CommandHandler<DeleteRestaurantCommand, Void> {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantUseCase(final RestaurantGateway restaurantGateway) {
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway);
    }

    @Override
    public Result<Void> handle(final DeleteRestaurantCommand command) {
        this.restaurantGateway.deleteById(RestaurantID.from(command.id()));
        return Result.success(null);
    }
}
