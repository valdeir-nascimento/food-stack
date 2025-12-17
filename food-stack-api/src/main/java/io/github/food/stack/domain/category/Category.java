package io.github.food.stack.domain.category;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.restaurant.RestaurantID;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryID> {

    private String name;
    private String description;
    private RestaurantID restaurantId;
    private Integer order;
    private Instant createdAt;
    private Instant updatedAt;

    private Category(
        final CategoryID id,
        final String name,
        final String description,
        final RestaurantID restaurantId,
        final Integer order,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.restaurantId = restaurantId;
        this.order = order;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Category newCategory(
        final String name,
        final String description,
        final RestaurantID restaurantId,
        final Integer order) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        return new Category(id, name, description, restaurantId, order, now, now);
    }

    public static Category with(
        final CategoryID id,
        final String name,
        final String description,
        final RestaurantID restaurantId,
        final Integer order,
        final Instant createdAt,
        final Instant updatedAt) {
        return new Category(id, name, description, restaurantId, order, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category update(
        final String name,
        final String description,
        final Integer order) {
        this.name = name;
        this.description = description;
        this.order = order;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RestaurantID getRestaurantId() {
        return restaurantId;
    }

    public Integer getOrder() {
        return order;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
