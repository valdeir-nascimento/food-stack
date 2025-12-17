package io.github.food.stack.application.customer.retrieve;

import io.github.food.stack.domain.customer.Customer;

import java.time.Instant;

public record CustomerOutput(
    String id,
    String name,
    String email,
    String phoneNumber,
    String address,
    Instant createdAt,
    Instant updatedAt
) {
    public static CustomerOutput from(final Customer customer) {
        return new CustomerOutput(
            customer.getId().getValue(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhoneNumber(),
            customer.getAddress(),
            customer.getCreatedAt(),
            customer.getUpdatedAt()
        );
    }
}
