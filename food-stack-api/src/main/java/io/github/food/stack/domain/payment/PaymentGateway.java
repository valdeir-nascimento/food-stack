package io.github.food.stack.domain.payment;

import java.util.Optional;

public interface PaymentGateway {

    Payment create(Payment payment);

    Payment update(Payment payment);

    Optional<Payment> findById(PaymentID id);

    void deleteById(PaymentID id);
}
