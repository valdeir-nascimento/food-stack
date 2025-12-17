package io.github.food.stack.application.order.create;

import java.math.BigDecimal;

public record CreateOrderCommand(
    String customerId,
    String restaurantId,
    BigDecimal totalAmount,
    String deliveryAddress,
    String notes
) {
    public static CreateOrderCommand with(
        final String customerId,
        final String restaurantId,
        final BigDecimal totalAmount,
        final String deliveryAddress,
        final String notes) {
        return new CreateOrderCommand(customerId, restaurantId, totalAmount, deliveryAddress, notes);
    }
}
