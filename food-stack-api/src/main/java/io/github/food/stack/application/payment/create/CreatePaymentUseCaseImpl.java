package io.github.food.stack.application.payment.create;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.order.OrderID;
import io.github.food.stack.domain.payment.Payment;
import io.github.food.stack.domain.payment.PaymentGateway;
import io.github.food.stack.domain.payment.PaymentMethod;
import io.github.food.stack.domain.validation.ValidationError;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public CreatePaymentUseCaseImpl(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public Result<CreatePaymentOutput> execute(final CreatePaymentCommand command) {
        final var notification = Notification.create();

        final var maybePaymentMethod = PaymentMethod.of(command.paymentMethod());
        if (maybePaymentMethod.isEmpty()) {
            notification.append(new ValidationError("'paymentMethod' is invalid"));
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var payment = Payment.newPayment(
                OrderID.from(command.orderId()),
                command.amount(),
                maybePaymentMethod.get());

        payment.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedPayment = this.paymentGateway.create(payment);

        return Result.success(CreatePaymentOutput.from(savedPayment));
    }
}
