package io.github.food.stack.domain.validation;

import java.util.Objects;

public record ValidationError(String message) {

    public ValidationError {
        Objects.requireNonNull(message, "message must not be null");
    }

    public static ValidationError of(String message) {
        return new ValidationError(message);
    }
}
