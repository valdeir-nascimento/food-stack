package io.github.food.stack.domain.restaurant;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Restaurant extends AggregateRoot<RestaurantID> {

    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;

    private Restaurant(
        final RestaurantID id,
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Restaurant newRestaurant(
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email) {
        final var id = RestaurantID.unique();
        final var now = Instant.now();
        return new Restaurant(id, name, description, address, phoneNumber, email, now, now);
    }

    public static Restaurant with(
        final RestaurantID id,
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email,
        final Instant createdAt,
        final Instant updatedAt) {
        return new Restaurant(id, name, description, address, phoneNumber, email, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new RestaurantValidator(this, handler).validate();
    }

    public Restaurant update(
        final String name,
        final String description,
        final String address,
        final String phoneNumber,
        final String email) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
