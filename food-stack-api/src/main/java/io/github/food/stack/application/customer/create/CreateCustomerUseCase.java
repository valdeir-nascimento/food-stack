package io.github.food.stack.application.customer.create;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.Customer;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateCustomerUseCase implements CommandHandler<CreateCustomerCommand, CreateCustomerOutput> {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<CreateCustomerOutput> handle(final CreateCustomerCommand command) {
        final var notification = Notification.create();

        final var customer = Customer.newCustomer(
            command.name(),
            command.email(),
            command.phoneNumber(),
            command.address()
        );

        customer.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedCustomer = this.customerGateway.create(customer);

        return Result.success(CreateCustomerOutput.from(savedCustomer));
    }
}
