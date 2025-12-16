package io.github.food.stack.domain.restaurant;

import io.github.food.stack.domain.core.Identifier;

import java.util.Objects;
import java.util.UUID;

public class RestaurantID extends Identifier<String> {

    private final String value;

    public RestaurantID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RestaurantID unique() {
        return RestaurantID.from(UUID.randomUUID());
    }

    public static RestaurantID from(final String id) {
        return new RestaurantID(id);
    }

    public static RestaurantID from(final UUID id) {
        return new RestaurantID(id.toString().toLowerCase());
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
        final RestaurantID that = (RestaurantID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
