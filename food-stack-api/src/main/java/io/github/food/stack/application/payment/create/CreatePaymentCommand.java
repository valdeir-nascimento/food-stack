package io.github.food.stack.application.payment.create;

import java.math.BigDecimal;

public record CreatePaymentCommand(
    String orderId,
    BigDecimal amount,
    String paymentMethod) {
    public static CreatePaymentCommand with(
        final String orderId,
        final BigDecimal amount,
        final String paymentMethod
    ) {
        return new CreatePaymentCommand(orderId, amount, paymentMethod);
    }
}
