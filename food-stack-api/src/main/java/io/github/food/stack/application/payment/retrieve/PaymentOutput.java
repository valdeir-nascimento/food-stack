package io.github.food.stack.application.payment.retrieve;

import io.github.food.stack.domain.payment.Payment;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentOutput(
    String id,
    String orderId,
    BigDecimal amount,
    String paymentMethod,
    String transactionId,
    Instant paymentDate,
    String status,
    Instant createdAt,
    Instant updatedAt
) {
    public static PaymentOutput from(final Payment payment) {
        return new PaymentOutput(
            payment.getId().getValue(),
            payment.getOrderId().getValue(),
            payment.getAmount(),
            payment.getPaymentMethod().getValue(),
            payment.getTransactionId(),
            payment.getPaymentDate(),
            payment.getStatus().getValue(),
            payment.getCreatedAt(),
            payment.getUpdatedAt());
    }
}
