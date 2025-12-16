package io.github.food.stack.domain.validation.handler;

import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final ValidationError error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(final ValidationHandler handler) {
        throw DomainException.with(handler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> validation) {
        try {
            return validation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(ValidationError.of(ex.getMessage()));
        }
    }

    @Override
    public List<ValidationError> getErrors() {
        return List.of();
    }
}
