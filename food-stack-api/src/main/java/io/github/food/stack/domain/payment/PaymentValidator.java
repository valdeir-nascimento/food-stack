package io.github.food.stack.domain.payment;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

import java.math.BigDecimal;

public class PaymentValidator extends Validator {

    private final Payment payment;

    public PaymentValidator(final Payment payment, final ValidationHandler handler) {
        super(handler);
        this.payment = payment;
    }

    @Override
    public void validate() {
        checkAmountConstraints();
    }

    private void checkAmountConstraints() {
        if (this.payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            this.validationHandler().append(new ValidationError("'amount' must be greater than zero"));
        }
    }
}
