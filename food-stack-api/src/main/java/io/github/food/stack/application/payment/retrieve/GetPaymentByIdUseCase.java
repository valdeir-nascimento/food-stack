package io.github.food.stack.application.payment.retrieve;

import io.github.food.stack.application.QueryHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.payment.Payment;
import io.github.food.stack.domain.payment.PaymentGateway;
import io.github.food.stack.domain.payment.PaymentID;

import java.util.Objects;

public class GetPaymentByIdUseCase implements QueryHandler<GetPaymentByIdQuery, PaymentOutput> {

    private final PaymentGateway paymentGateway;

    public GetPaymentByIdUseCase(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public Result<PaymentOutput> handle(final GetPaymentByIdQuery query) {
        final var id = PaymentID.from(query.id());

        return this.paymentGateway.findById(id)
            .map(PaymentOutput::from)
            .map(Result::success)
            .orElseGet(() -> Result.failure(NotFoundException.with(Payment.class, id)));
    }
}
