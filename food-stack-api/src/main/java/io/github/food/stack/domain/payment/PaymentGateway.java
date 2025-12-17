package io.github.food.stack.domain.payment;

public interface PaymentGateway {

    Payment create(Payment payment);

    Payment update(Payment payment);

    Payment findById(PaymentID id);

    void deleteById(PaymentID id);
}
