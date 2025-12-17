package io.github.food.stack.application.restaurant.retrieve;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.restaurant.Restaurant;
import io.github.food.stack.domain.restaurant.RestaurantGateway;
import io.github.food.stack.domain.restaurant.RestaurantID;

import java.util.Objects;

public class GetRestaurantByIdUseCaseImpl implements GetRestaurantByIdUseCase {

    private final RestaurantGateway restaurantGateway;

    public GetRestaurantByIdUseCaseImpl(final RestaurantGateway restaurantGateway) {
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway);
    }

    @Override
    public Result<RestaurantOutput> execute(final GetRestaurantByIdQuery query) {
        final var id = RestaurantID.from(query.id());

        return this.restaurantGateway.findById(id)
                .map(RestaurantOutput::from)
                .map(Result::success)
                .orElseGet(() -> Result.failure(NotFoundException.with(Restaurant.class, id)));
    }
}
