package io.github.food.stack.domain.payment;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    FAILED("failed"),
    REFUNDED("refunded");

    private final String value;

    PaymentStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<PaymentStatus> of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
