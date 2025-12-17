package io.github.food.stack.application.order.create;

import io.github.food.stack.domain.order.Order;

public record CreateOrderOutput(String id, String status) {

    public static CreateOrderOutput from(final Order order) {
        return new CreateOrderOutput(
            order.getId().getValue(),
            order.getStatus().getValue()
        );
    }
}
