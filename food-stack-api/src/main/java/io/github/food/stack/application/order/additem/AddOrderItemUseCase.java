package io.github.food.stack.application.order.additem;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.menuitem.MenuItemID;
import io.github.food.stack.domain.order.Order;
import io.github.food.stack.domain.order.OrderGateway;
import io.github.food.stack.domain.order.OrderID;
import io.github.food.stack.domain.order.OrderItem;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class AddOrderItemUseCase implements CommandHandler<AddOrderItemCommand, AddOrderItemOutput> {

    private final OrderGateway orderGateway;

    public AddOrderItemUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public Result<AddOrderItemOutput> handle(final AddOrderItemCommand command) {
        final var orderId = OrderID.from(command.orderId());

        final var maybeOrder = this.orderGateway.findById(orderId);
        if (maybeOrder.isEmpty()) {
            return Result.failure(NotFoundException.with(Order.class, orderId));
        }

        final var order = maybeOrder.get();
        final var notification = Notification.create();

        final var orderItem = OrderItem.newOrderItem(
            orderId,
            MenuItemID.from(command.menuItemId()),
            command.quantity(),
            command.priceAtOrder(),
            command.notes()
        );

        orderItem.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        order.addOrderItem(orderItem);
        final var updatedOrder = this.orderGateway.update(order);

        return Result.success(AddOrderItemOutput.from(updatedOrder));
    }
}
