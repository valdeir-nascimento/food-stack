package io.github.food.stack.domain.validation.handler;

import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<ValidationError> validationErrors;

    private Notification(final List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable t) {
        return create(new ValidationError(t.getMessage()));
    }

    public static Notification create(final ValidationError anValidationError) {
        return new Notification(new ArrayList<>()).append(anValidationError);
    }

    @Override
    public Notification append(final ValidationError anValidationError) {
        this.validationErrors.add(anValidationError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.validationErrors.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final DomainException ex) {
            this.validationErrors.addAll(ex.getErrors());
        } catch (final Throwable t) {
            this.validationErrors.add(new ValidationError(t.getMessage()));
        }
        return null;
    }

    @Override
    public List<ValidationError> getErrors() {
        return this.validationErrors;
    }
}
