package io.github.food.stack.domain.menuitem;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

import java.math.BigDecimal;

public class MenuItemValidator extends Validator {

    private final MenuItem menuItem;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public MenuItemValidator(final MenuItem menuItem, final ValidationHandler handler) {
        super(handler);
        this.menuItem = menuItem;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkPriceConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.menuItem.getName();
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

    private void checkPriceConstraints() {
        if (this.menuItem.getPrice() == null) {
            this.validationHandler().append(new ValidationError("'price' should not be null"));
            return;
        }
        if (this.menuItem.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            this.validationHandler().append(new ValidationError("'price' must be greater than zero"));
        }
    }
}
