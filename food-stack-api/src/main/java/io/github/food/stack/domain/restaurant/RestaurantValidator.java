package io.github.food.stack.domain.restaurant;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

public class RestaurantValidator extends Validator {

    private final Restaurant restaurant;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int EMAIL_MAX_LENGTH = 255;
    private static final int PHONE_MAX_LENGTH = 20;

    public RestaurantValidator(final Restaurant restaurant, final ValidationHandler handler) {
        super(handler);
        this.restaurant = restaurant;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkEmailConstraints();
        checkPhoneNumberConstraints();
        checkAddressConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.restaurant.getName();
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
        final var email = this.restaurant.getEmail();
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
        final var phoneNumber = this.restaurant.getPhoneNumber();
        if (phoneNumber == null) {
            this.validationHandler().append(new ValidationError("'phoneNumber' should not be null"));
            return;
        }

        if (phoneNumber.isBlank()) {
            this.validationHandler().append(new ValidationError("'phoneNumber' should not be empty"));
            return;
        }

        if (phoneNumber.length() > PHONE_MAX_LENGTH) {
            this.validationHandler().append(new ValidationError("'phoneNumber' must be less than 20 characters"));
        }
    }

    private void checkAddressConstraints() {
        final var address = this.restaurant.getAddress();
        if (address == null) {
            this.validationHandler().append(new ValidationError("'address' should not be null"));
            return;
        }

        if (address.isBlank()) {
            this.validationHandler().append(new ValidationError("'address' should not be empty"));
        }
    }
}
