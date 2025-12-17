package io.github.food.stack.domain.order;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.customer.CustomerID;
import io.github.food.stack.domain.restaurant.RestaurantID;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order extends AggregateRoot<OrderID> {

    private CustomerID customerId;
    private RestaurantID restaurantId;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private Instant orderDate;
    private String deliveryAddress;
    private String notes;
    private Set<OrderItem> items;
    private Instant createdAt;
    private Instant updatedAt;

    private Order(
        final OrderID id,
        final CustomerID customerId,
        final RestaurantID restaurantId,
        final BigDecimal totalAmount,
        final OrderStatus status,
        final Instant orderDate,
        final String deliveryAddress,
        final String notes,
        final Set<OrderItem> items,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.notes = notes;
        this.items = items;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Order newOrder(
        final CustomerID customerId,
        final RestaurantID restaurantId,
        final BigDecimal totalAmount,
        final String deliveryAddress,
        final String notes) {
        final var id = OrderID.unique();
        final var now = Instant.now();
        return new Order(
            id,
            customerId,
            restaurantId,
            totalAmount,
            OrderStatus.PENDING,
            now,
            deliveryAddress,
            notes,
            new HashSet<>(),
            now,
            now
        );
    }

    public static Order with(
        final OrderID id,
        final CustomerID customerId,
        final RestaurantID restaurantId,
        final BigDecimal totalAmount,
        final OrderStatus status,
        final Instant orderDate,
        final String deliveryAddress,
        final String notes,
        final Set<OrderItem> items,
        final Instant createdAt,
        final Instant updatedAt) {
        return new Order(
            id,
            customerId,
            restaurantId,
            totalAmount,
            status,
            orderDate,
            deliveryAddress,
            notes,
            new HashSet<>(items),
            createdAt,
            updatedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public Order updateStatus(final OrderStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
        return this;
    }

    public Order addOrderItem(final OrderItem item) {
        if (item != null) {
            this.items.add(item);
            this.updatedAt = Instant.now();
        }
        return this;
    }

    public Order removeItem(final OrderItem item) {
        if (item != null) {
            this.items.remove(item);
            this.updatedAt = Instant.now();
        }
        return this;
    }

    public CustomerID getCustomerId() {
        return customerId;
    }

    public RestaurantID getRestaurantId() {
        return restaurantId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getNotes() {
        return notes;
    }

    public Set<OrderItem> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
