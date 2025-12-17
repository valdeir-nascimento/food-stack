package io.github.food.stack.application.payment.refund;

import io.github.food.stack.domain.payment.Payment;

public record RefundPaymentOutput(String id, String status) {

    public static RefundPaymentOutput from(final Payment payment) {
        return new RefundPaymentOutput(
            payment.getId().getValue(),
            payment.getStatus().getValue()
        );
    }
}
