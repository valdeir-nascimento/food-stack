package io.github.food.stack.application.order.updatestatus;

public record UpdateOrderStatusCommand(
    String orderId,
    String status
) {
    public static UpdateOrderStatusCommand with(
        final String orderId,
        final String status
    ) {
        return new UpdateOrderStatusCommand(orderId, status);
    }
}
