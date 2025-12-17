package io.github.food.stack.domain.payment;

import io.github.food.stack.domain.core.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PaymentID extends Identifier<String> {

    private final String value;

    public PaymentID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PaymentID unique() {
        return PaymentID.from(UUID.randomUUID());
    }

    public static PaymentID from(final String id) {
        return new PaymentID(id);
    }

    public static PaymentID from(final UUID id) {
        return new PaymentID(id.toString().toLowerCase());
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
        final PaymentID that = (PaymentID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
