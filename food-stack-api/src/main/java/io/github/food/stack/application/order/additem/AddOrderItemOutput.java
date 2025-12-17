package io.github.food.stack.application.order.additem;

import io.github.food.stack.domain.order.Order;

public record AddOrderItemOutput(String orderId, int totalItems) {

    public static AddOrderItemOutput from(final Order order) {
        return new AddOrderItemOutput(
            order.getId().getValue(),
            order.getItems().size()
        );
    }
}
