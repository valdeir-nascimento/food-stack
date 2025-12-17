package io.github.food.stack.application.order.retrieve;

import io.github.food.stack.domain.order.Order;
import io.github.food.stack.domain.order.OrderItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderOutput(
    String id,
    String customerId,
    String restaurantId,
    BigDecimal totalAmount,
    String status,
    Instant orderDate,
    String deliveryAddress,
    String notes,
    Set<OrderItemOutput> items,
    Instant createdAt,
    Instant updatedAt
) {
    public static OrderOutput from(final Order order) {
        final var items = order.getItems().stream()
            .map(OrderItemOutput::from)
            .collect(Collectors.toSet());

        return new OrderOutput(
            order.getId().getValue(),
            order.getCustomerId().getValue(),
            order.getRestaurantId().getValue(),
            order.getTotalAmount(),
            order.getStatus().getValue(),
            order.getOrderDate(),
            order.getDeliveryAddress(),
            order.getNotes(),
            items,
            order.getCreatedAt(),
            order.getUpdatedAt());
    }

    public record OrderItemOutput(
        String id,
        String menuItemId,
        int quantity,
        BigDecimal priceAtOrder,
        String notes
    ) {
        public static OrderItemOutput from(final OrderItem item) {
            return new OrderItemOutput(
                item.getId().getValue(),
                item.getMenuItemId().getValue(),
                item.getQuantity(),
                item.getPriceAtOrder(),
                item.getNotes());
        }
    }
}
