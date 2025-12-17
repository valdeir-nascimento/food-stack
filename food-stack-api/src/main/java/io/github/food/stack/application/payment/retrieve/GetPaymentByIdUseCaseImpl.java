package io.github.food.stack.application.payment.retrieve;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.payment.Payment;
import io.github.food.stack.domain.payment.PaymentGateway;
import io.github.food.stack.domain.payment.PaymentID;

import java.util.Objects;

public class GetPaymentByIdUseCaseImpl implements GetPaymentByIdUseCase {

    private final PaymentGateway paymentGateway;

    public GetPaymentByIdUseCaseImpl(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public Result<PaymentOutput> execute(final GetPaymentByIdQuery query) {
        final var id = PaymentID.from(query.id());

        return this.paymentGateway.findById(id)
            .map(PaymentOutput::from)
            .map(Result::success)
            .orElseGet(() -> Result.failure(NotFoundException.with(Payment.class, id)));
    }
}
