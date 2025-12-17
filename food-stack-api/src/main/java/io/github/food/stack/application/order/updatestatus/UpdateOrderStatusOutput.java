package io.github.food.stack.application.order.updatestatus;

import io.github.food.stack.domain.order.Order;

public record UpdateOrderStatusOutput(String id, String status) {

    public static UpdateOrderStatusOutput from(final Order order) {
        return new UpdateOrderStatusOutput(
            order.getId().getValue(),
            order.getStatus().getValue()
        );
    }
}
