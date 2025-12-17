package io.github.food.stack.application.restaurant.delete;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.restaurant.RestaurantGateway;
import io.github.food.stack.domain.restaurant.RestaurantID;

import java.util.Objects;

public class DeleteRestaurantUseCaseImpl implements DeleteRestaurantUseCase {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantUseCaseImpl(final RestaurantGateway restaurantGateway) {
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway);
    }

    @Override
    public Result<Void> execute(final DeleteRestaurantCommand command) {
        this.restaurantGateway.deleteById(RestaurantID.from(command.id()));
        return Result.success(null);
    }
}
