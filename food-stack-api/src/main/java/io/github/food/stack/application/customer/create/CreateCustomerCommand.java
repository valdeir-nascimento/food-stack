package io.github.food.stack.application.customer.create;

public record CreateCustomerCommand(
    String name,
    String email,
    String phoneNumber,
    String address
) {
    public static CreateCustomerCommand with(
        final String name,
        final String email,
        final String phoneNumber,
        final String address) {
        return new CreateCustomerCommand(name, email, phoneNumber, address);
    }
}
