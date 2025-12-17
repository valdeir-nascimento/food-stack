package io.github.food.stack.application.order.additem;

import java.math.BigDecimal;

public record AddOrderItemCommand(
    String orderId,
    String menuItemId,
    int quantity,
    BigDecimal priceAtOrder,
    String notes
) {
    public static AddOrderItemCommand with(
        final String orderId,
        final String menuItemId,
        final int quantity,
        final BigDecimal priceAtOrder,
        final String notes
    ) {
        return new AddOrderItemCommand(orderId, menuItemId, quantity, priceAtOrder, notes);
    }
}
