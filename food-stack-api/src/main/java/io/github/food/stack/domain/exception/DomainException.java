package io.github.food.stack.domain.exception;

import io.github.food.stack.domain.validation.ValidationError;

import java.util.List;

public class DomainException extends NoStacktraceException {

    protected final List<ValidationError> validationErrors;

    protected DomainException(final String aMessage, final List<ValidationError> anValidationErrors) {
        super(aMessage);
        this.validationErrors = anValidationErrors;
    }

    public static DomainException with(final ValidationError anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<ValidationError> anValidationErrors) {
        return new DomainException("", anValidationErrors);
    }

    public List<ValidationError> getErrors() {
        return validationErrors;
    }
}
