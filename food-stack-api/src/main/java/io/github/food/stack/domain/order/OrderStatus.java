package io.github.food.stack.domain.order;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    PENDING("pending"),
    CONFIRMED("confirmed"),
    PREPARING("preparing"),
    READY("ready"),
    ON_THE_WAY("on_the_way"),
    DELIVERED("delivered"),
    CANCELLED("cancelled"),
    REFUNDED("refunded");

    private final String value;

    OrderStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<OrderStatus> of(final String value) {
        return Arrays.stream(values())
            .filter(it -> it.value.equalsIgnoreCase(value))
            .findFirst();
    }
}
