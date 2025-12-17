package io.github.food.stack.domain.order;

import io.github.food.stack.domain.core.Entity;
import io.github.food.stack.domain.menuitem.MenuItemID;
import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class OrderItem extends Entity<OrderItemID> {

    private OrderID orderId;
    private MenuItemID menuItemId;
    private int quantity;
    private BigDecimal priceAtOrder;
    private String notes;
    private Instant createdAt;
    private Instant updatedAt;

    private OrderItem(
        final OrderItemID id,
        final OrderID orderId,
        final MenuItemID menuItemId,
        final int quantity,
        final BigDecimal priceAtOrder,
        final String notes,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
        this.notes = notes;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static OrderItem newOrderItem(
        final OrderID orderId,
        final MenuItemID menuItemId,
        final int quantity,
        final BigDecimal priceAtOrder,
        final String notes) {
        final var id = OrderItemID.unique();
        final var now = Instant.now();
        return new OrderItem(id, orderId, menuItemId, quantity, priceAtOrder, notes, now, now);
    }

    public static OrderItem with(
        final OrderItemID id,
        final OrderID orderId,
        final MenuItemID menuItemId,
        final int quantity,
        final BigDecimal priceAtOrder,
        final String notes,
        final Instant createdAt,
        final Instant updatedAt) {
        return new OrderItem(id, orderId, menuItemId, quantity, priceAtOrder, notes, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        if (this.orderId == null) {
            handler.append(new ValidationError("'orderId' should not be null"));
        }
        if (this.menuItemId == null) {
            handler.append(new ValidationError("'menuItemId' should not be null"));
        }
        if (this.quantity <= 0) {
            handler.append(new ValidationError("'quantity' must be greater than zero"));
        }
        if (this.priceAtOrder == null) {
            handler.append(new ValidationError("'priceAtOrder' should not be null"));
        } else if (this.priceAtOrder.compareTo(BigDecimal.ZERO) < 0) {
            handler.append(new ValidationError("'priceAtOrder' must be greater than zero"));
        }
    }

    public OrderID getOrderId() {
        return orderId;
    }

    public MenuItemID getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPriceAtOrder() {
        return priceAtOrder;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
