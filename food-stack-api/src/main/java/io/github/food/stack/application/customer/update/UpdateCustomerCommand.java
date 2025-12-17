package io.github.food.stack.application.customer.update;

public record UpdateCustomerCommand(
    String id,
    String name,
    String email,
    String phoneNumber,
    String address
) {
    public static UpdateCustomerCommand with(
        final String id,
        final String name,
        final String email,
        final String phoneNumber,
        final String address) {
        return new UpdateCustomerCommand(id, name, email, phoneNumber, address);
    }
}
