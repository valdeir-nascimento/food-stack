package io.github.food.stack.application.customer.retrieve;

public record GetCustomerByIdQuery(String id) {

    public static GetCustomerByIdQuery with(final String id) {
        return new GetCustomerByIdQuery(id);
    }
}
