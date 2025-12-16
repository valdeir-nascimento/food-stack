package io.github.food.stack.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(ValidationError anValidationError);

    ValidationHandler append(ValidationHandler anHandler);

    <T> T validate(Validation<T> aValidation);

    List<ValidationError> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default ValidationError firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        } else {
            return null;
        }
    }

    public interface Validation<T> {
        T validate();
    }
}
