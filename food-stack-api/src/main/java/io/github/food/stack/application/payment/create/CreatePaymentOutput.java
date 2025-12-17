package io.github.food.stack.application.payment.create;

import io.github.food.stack.domain.payment.Payment;

public record CreatePaymentOutput(String id) {

    public static CreatePaymentOutput from(final Payment payment) {
        return new CreatePaymentOutput(payment.getId().getValue());
    }
}
