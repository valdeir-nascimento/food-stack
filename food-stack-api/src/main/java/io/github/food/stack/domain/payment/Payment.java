package io.github.food.stack.domain.payment;

import io.github.food.stack.domain.core.AggregateRoot;
import io.github.food.stack.domain.order.OrderID;
import io.github.food.stack.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Payment extends AggregateRoot<PaymentID> {

    private final OrderID orderId;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;
    private String transactionId;
    private Instant paymentDate;
    private PaymentStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    private Payment(
        final PaymentID id,
        final OrderID orderId,
        final BigDecimal amount,
        final PaymentMethod paymentMethod,
        final String transactionId,
        final Instant paymentDate,
        final PaymentStatus status,
        final Instant createdAt,
        final Instant updatedAt) {
        super(id);
        this.orderId = Objects.requireNonNull(orderId, "'orderId' should not be null");
        this.amount = Objects.requireNonNull(amount, "'amount' should not be null");
        this.paymentMethod = Objects.requireNonNull(paymentMethod, "'paymentMethod' should not be null");
        this.transactionId = transactionId;
        this.paymentDate = Objects.requireNonNull(paymentDate, "'paymentDate' should not be null");
        this.status = Objects.requireNonNull(status, "'status' should not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Payment newPayment(
        final OrderID orderId,
        final BigDecimal amount,
        final PaymentMethod paymentMethod) {
        final var id = PaymentID.unique();
        final var now = Instant.now();
        return new Payment(id, orderId, amount, paymentMethod, null, now, PaymentStatus.PENDING, now, now);
    }

    public static Payment with(
        final PaymentID id,
        final OrderID orderId,
        final BigDecimal amount,
        final PaymentMethod paymentMethod,
        final String transactionId,
        final Instant paymentDate,
        final PaymentStatus status,
        final Instant createdAt,
        final Instant updatedAt) {
        return new Payment(id, orderId, amount, paymentMethod, transactionId, paymentDate, status, createdAt,
            updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PaymentValidator(this, handler).validate();
    }

    public Payment complete(final String transactionId) {
        this.transactionId = transactionId;
        this.status = PaymentStatus.COMPLETED;
        this.updatedAt = Instant.now();
        return this;
    }

    public Payment fail() {
        this.status = PaymentStatus.FAILED;
        this.updatedAt = Instant.now();
        return this;
    }

    public Payment refund() {
        this.status = PaymentStatus.REFUNDED;
        this.updatedAt = Instant.now();
        return this;
    }

    public OrderID getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
