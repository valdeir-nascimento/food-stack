package io.github.food.stack.application.order.updatestatus;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.order.Order;
import io.github.food.stack.domain.order.OrderGateway;
import io.github.food.stack.domain.order.OrderID;
import io.github.food.stack.domain.order.OrderStatus;
import io.github.food.stack.domain.validation.ValidationError;

import java.util.Objects;

public class UpdateOrderStatusUseCase implements CommandHandler<UpdateOrderStatusCommand, UpdateOrderStatusOutput> {

    private final OrderGateway orderGateway;

    public UpdateOrderStatusUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public Result<UpdateOrderStatusOutput> handle(final UpdateOrderStatusCommand command) {
        final var id = OrderID.from(command.orderId());

        final var maybeOrder = this.orderGateway.findById(id);
        if (maybeOrder.isEmpty()) {
            return Result.failure(NotFoundException.with(Order.class, id));
        }

        final var maybeStatus = OrderStatus.of(command.status());
        if (maybeStatus.isEmpty()) {
            return Result.failure(DomainException.with(new ValidationError("'status' is invalid")));
        }

        final var order = maybeOrder.get();
        order.updateStatus(maybeStatus.get());

        final var updatedOrder = this.orderGateway.update(order);

        return Result.success(UpdateOrderStatusOutput.from(updatedOrder));
    }
}
