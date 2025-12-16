package io.github.food.stack.domain.customer;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

public class CustomerValidator extends Validator {

    private final Customer customer;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int EMAIL_MAX_LENGTH = 255;
    private static final int PHONE_MAX_LENGTH = 20;

    public CustomerValidator(final Customer customer, final ValidationHandler handler) {
        super(handler);
        this.customer = customer;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkEmailConstraints();
        checkPhoneNumberConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.customer.getName();
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

    private void checkEmailConstraints() {
        final var email = this.customer.getEmail();
        if (email == null) {
            this.validationHandler().append(new ValidationError("'email' should not be null"));
            return;
        }

        if (email.isBlank()) {
            this.validationHandler().append(new ValidationError("'email' should not be empty"));
            return;
        }

        final int length = email.trim().length();
        if (length > EMAIL_MAX_LENGTH) {
            this.validationHandler().append(new ValidationError("'email' must be less than 255 characters"));
        }
    }

    private void checkPhoneNumberConstraints() {
        final var phoneNumber = this.customer.getPhoneNumber();
        if (phoneNumber != null && phoneNumber.length() > PHONE_MAX_LENGTH) {
            this.validationHandler().append(new ValidationError("'phoneNumber' must be less than 20 characters"));
        }
    }
}
