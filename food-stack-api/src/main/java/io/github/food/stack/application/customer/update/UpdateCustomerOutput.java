package io.github.food.stack.application.customer.update;

import io.github.food.stack.domain.customer.Customer;

public record UpdateCustomerOutput(String id) {

    public static UpdateCustomerOutput from(final Customer customer) {
        return new UpdateCustomerOutput(customer.getId().getValue());
    }
}
