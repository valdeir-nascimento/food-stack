package io.github.food.stack.application.payment.complete;

public record CompletePaymentCommand(
    String paymentId,
    String transactionId) {
    public static CompletePaymentCommand with(
        final String paymentId,
        final String transactionId) {
        return new CompletePaymentCommand(paymentId, transactionId);
    }
}
