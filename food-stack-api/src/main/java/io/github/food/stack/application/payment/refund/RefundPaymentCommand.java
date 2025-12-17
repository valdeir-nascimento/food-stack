package io.github.food.stack.application.payment.refund;

public record RefundPaymentCommand(String paymentId) {

    public static RefundPaymentCommand with(final String paymentId) {
        return new RefundPaymentCommand(paymentId);
    }
}
