package io.github.food.stack.domain.customer;

import io.github.food.stack.domain.core.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CustomerID extends Identifier<String> {

    private final String value;

    public CustomerID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CustomerID unique() {
        return CustomerID.from(UUID.randomUUID());
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId);
    }

    public static CustomerID from(final UUID anId) {
        return new CustomerID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final CustomerID that = (CustomerID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
