package io.github.food.stack.application.customer.delete;

public record DeleteCustomerCommand(String id) {

    public static DeleteCustomerCommand with(final String id) {
        return new DeleteCustomerCommand(id);
    }
}
