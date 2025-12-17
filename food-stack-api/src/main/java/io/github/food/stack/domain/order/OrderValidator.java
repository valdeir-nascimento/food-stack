package io.github.food.stack.domain.order;

import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.ValidationHandler;
import io.github.food.stack.domain.validation.Validator;

import java.math.BigDecimal;

public class OrderValidator extends Validator {

    private final Order order;

    public OrderValidator(final Order order, final ValidationHandler handler) {
        super(handler);
        this.order = order;
    }

    @Override
    public void validate() {
        checkCustomerIdConstraints();
        checkRestaurantIdConstraints();
        checkTotalAmountConstraints();
        checkStatusConstraints();
        checkOrderDateConstraints();
    }

    private void checkCustomerIdConstraints() {
        if (this.order.getCustomerId() == null) {
            this.validationHandler().append(new ValidationError("'customerId' should not be null"));
        }
    }

    private void checkRestaurantIdConstraints() {
        if (this.order.getRestaurantId() == null) {
            this.validationHandler().append(new ValidationError("'restaurantId' should not be null"));
        }
    }

    private void checkTotalAmountConstraints() {
        if (this.order.getTotalAmount() == null) {
            this.validationHandler().append(new ValidationError("'totalAmount' should not be null"));
            return;
        }
        if (this.order.getTotalAmount().compareTo(BigDecimal.ZERO) < 0) {
            this.validationHandler().append(new ValidationError("'totalAmount' must be greater than zero"));
        }
    }

    private void checkStatusConstraints() {
        if (this.order.getStatus() == null) {
            this.validationHandler().append(new ValidationError("'status' should not be null"));
        }
    }

    private void checkOrderDateConstraints() {
        if (this.order.getOrderDate() == null) {
            this.validationHandler().append(new ValidationError("'orderDate' should not be null"));
        }
    }
}
