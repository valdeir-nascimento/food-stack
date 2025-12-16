package io.github.food.stack.domain.exception;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.core.Identifier;
import io.github.food.stack.domain.validation.ValidationError;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String message, final List<ValidationError> errors) {
        super(message, errors);
    }

    public static NotFoundException with(
        final Class<? extends AggregateRoot<?>> anAggregate,
        final Identifier<?> id
    ) {
        final var anError = "%s with ID %s was not found".formatted(anAggregate.getSimpleName(), id.getValue());
        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(
        final Class<?> anEntity,
        final Object id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
            anEntity.getSimpleName(),
            id
        );
        return new NotFoundException(anError, Collections.emptyList());
    }
}
