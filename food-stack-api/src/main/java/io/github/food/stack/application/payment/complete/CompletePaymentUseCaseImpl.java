package io.github.food.stack.application.payment.complete;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.payment.Payment;
import io.github.food.stack.domain.payment.PaymentGateway;
import io.github.food.stack.domain.payment.PaymentID;

import java.util.Objects;

public class CompletePaymentUseCaseImpl implements CompletePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public CompletePaymentUseCaseImpl(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public Result<CompletePaymentOutput> execute(final CompletePaymentCommand command) {
        final var id = PaymentID.from(command.paymentId());

        final var maybePayment = this.paymentGateway.findById(id);
        if (maybePayment.isEmpty()) {
            return Result.failure(NotFoundException.with(Payment.class, id));
        }

        final var payment = maybePayment.get();
        payment.complete(command.transactionId());

        final var updatedPayment = this.paymentGateway.update(payment);

        return Result.success(CompletePaymentOutput.from(updatedPayment));
    }
}
