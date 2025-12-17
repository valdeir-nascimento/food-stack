package io.github.food.stack.application.payment.refund;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.payment.Payment;
import io.github.food.stack.domain.payment.PaymentGateway;
import io.github.food.stack.domain.payment.PaymentID;

import java.util.Objects;

public class RefundPaymentUseCaseImpl implements RefundPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public RefundPaymentUseCaseImpl(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public Result<RefundPaymentOutput> execute(final RefundPaymentCommand command) {
        final var id = PaymentID.from(command.paymentId());

        final var maybePayment = this.paymentGateway.findById(id);
        if (maybePayment.isEmpty()) {
            return Result.failure(NotFoundException.with(Payment.class, id));
        }

        final var payment = maybePayment.get();
        payment.refund();

        final var updatedPayment = this.paymentGateway.update(payment);

        return Result.success(RefundPaymentOutput.from(updatedPayment));
    }
}
