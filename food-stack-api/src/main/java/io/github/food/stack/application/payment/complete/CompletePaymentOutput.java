package io.github.food.stack.application.payment.complete;

import io.github.food.stack.domain.payment.Payment;

public record CompletePaymentOutput(String id, String status) {

    public static CompletePaymentOutput from(final Payment payment) {
        return new CompletePaymentOutput(
            payment.getId().getValue(),
            payment.getStatus().getValue()
        );
    }
}
