package io.github.food.stack.domain.menuitem;

import io.github.food.stack.domain.core.Identifier;

import java.util.Objects;
import java.util.UUID;

public class MenuItemID extends Identifier<String> {

    private final String value;

    public MenuItemID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static MenuItemID unique() {
        return MenuItemID.from(UUID.randomUUID());
    }

    public static MenuItemID from(final String id) {
        return new MenuItemID(id);
    }

    public static MenuItemID from(final UUID id) {
        return new MenuItemID(id.toString().toLowerCase());
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
        final MenuItemID that = (MenuItemID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
