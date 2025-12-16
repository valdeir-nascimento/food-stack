package io.github.food.stack.domain.customer;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Customer extends AggregateRoot<CustomerID> {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Instant createdAt;
    private Instant updatedAt;

    private Customer(
        final CustomerID id,
        final String name,
        final String email,
        final String phoneNumber,
        final String address,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Customer newCustomer(
        final String name,
        final String email,
        final String phoneNumber,
        final String address) {
        final var id = CustomerID.unique();
        final var now = Instant.now();
        return new Customer(id, name, email, phoneNumber, address, now, now);
    }

    public static Customer with(
        final CustomerID id,
        final String name,
        final String email,
        final String phoneNumber,
        final String address,
        final Instant createdAt,
        final Instant updatedAt) {
        return new Customer(id, name, email, phoneNumber, address, createdAt, updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }

    public Customer update(
        final String name,
        final String email,
        final String phoneNumber,
        final String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
