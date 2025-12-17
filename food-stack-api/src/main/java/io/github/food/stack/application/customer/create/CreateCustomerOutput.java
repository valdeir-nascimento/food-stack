package io.github.food.stack.application.customer.create;

import io.github.food.stack.domain.customer.Customer;

public record CreateCustomerOutput(String id) {

    public static CreateCustomerOutput from(final Customer customer) {
        return new CreateCustomerOutput(customer.getId().getValue());
    }
}
