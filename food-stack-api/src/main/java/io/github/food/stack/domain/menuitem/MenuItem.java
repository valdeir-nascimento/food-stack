package io.github.food.stack.domain.menuitem;

import io.github.food.stack.domain.category.CategoryID;
import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class MenuItem extends AggregateRoot<MenuItemID> {

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private boolean available;
    private final CategoryID categoryId;
    private Instant createdAt;
    private Instant updatedAt;

    private MenuItem(
        final MenuItemID id,
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available,
        final CategoryID categoryId,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.available = available;
        this.categoryId = Objects.requireNonNull(categoryId, "'categoryId' should not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static MenuItem newMenuItem(
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available,
        final CategoryID categoryId) {
        final var id = MenuItemID.unique();
        final var now = Instant.now();
        return new MenuItem(id, name, description, price, imageUrl, available, categoryId, now, now);
    }

    public static MenuItem with(
        final MenuItemID id,
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available,
        final CategoryID categoryId,
        final Instant createdAt,
        final Instant updatedAt) {
        return new MenuItem(id, name, description, price, imageUrl, available, categoryId, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new MenuItemValidator(this, handler).validate();
    }

    public MenuItem update(
        final String name,
        final String description,
        final BigDecimal price,
        final String imageUrl,
        final boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.available = available;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public CategoryID getCategoryId() {
        return categoryId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
