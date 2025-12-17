package io.github.food.stack.application.customer.create;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.Customer;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCaseImpl(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<CreateCustomerOutput> execute(final CreateCustomerCommand command) {
        final var notification = Notification.create();

        final var customer = Customer.newCustomer(
                command.name(),
                command.email(),
                command.phoneNumber(),
                command.address());

        customer.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedCustomer = this.customerGateway.create(customer);

        return Result.success(CreateCustomerOutput.from(savedCustomer));
    }
}
