package io.github.food.stack.domain.category;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkRestaurantIdConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new ValidationError("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new ValidationError("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new ValidationError("'name' must be between 3 and 255 characters"));
        }
    }

    private void checkRestaurantIdConstraints() {
        if (this.category.getRestaurantId() == null) {
            this.validationHandler().append(new ValidationError("'restaurantId' should not be null"));
        }
    }
}
