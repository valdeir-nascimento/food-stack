package io.github.food.stack.domain.payment;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMethod {
    CREDIT_CARD("credit_card"),
    DEBIT_CARD("debit_card"),
    CASH("cash"),
    PIX("pix"),
    ONLINE_PAYMENT("online_payment");

    private final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<PaymentMethod> of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
