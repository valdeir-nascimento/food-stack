package io.github.food.stack.application.payment.retrieve;

public record GetPaymentByIdQuery(String id) {

    public static GetPaymentByIdQuery with(final String id) {
        return new GetPaymentByIdQuery(id);
    }
}
