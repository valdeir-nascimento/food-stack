package io.github.food.stack.application.order.create;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.CustomerID;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.order.Order;
import io.github.food.stack.domain.order.OrderGateway;
import io.github.food.stack.domain.restaurant.RestaurantID;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateOrderUseCase implements CommandHandler<CreateOrderCommand, CreateOrderOutput> {

    private final OrderGateway orderGateway;

    public CreateOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public Result<CreateOrderOutput> handle(final CreateOrderCommand command) {
        final var notification = Notification.create();

        final var order = Order.newOrder(
            CustomerID.from(command.customerId()),
            RestaurantID.from(command.restaurantId()),
            command.totalAmount(),
            command.deliveryAddress(),
            command.notes()
        );

        order.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedOrder = this.orderGateway.create(order);

        return Result.success(CreateOrderOutput.from(savedOrder));
    }
}
