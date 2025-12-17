package io.github.food.stack.application.order.retrieve;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.order.Order;
import io.github.food.stack.domain.order.OrderGateway;
import io.github.food.stack.domain.order.OrderID;

import java.util.Objects;

public class GetOrderByIdUseCaseImpl implements GetOrderByIdUseCase {

    private final OrderGateway orderGateway;

    public GetOrderByIdUseCaseImpl(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public Result<OrderOutput> execute(final GetOrderByIdQuery query) {
        final var id = OrderID.from(query.id());

        return this.orderGateway.findById(id)
                .map(OrderOutput::from)
                .map(Result::success)
                .orElseGet(() -> Result.failure(NotFoundException.with(Order.class, id)));
    }
}
