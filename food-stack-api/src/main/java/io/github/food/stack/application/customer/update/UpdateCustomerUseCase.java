package io.github.food.stack.application.customer.update;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.Customer;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.customer.CustomerID;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class UpdateCustomerUseCase implements CommandHandler<UpdateCustomerCommand, UpdateCustomerOutput> {

    private final CustomerGateway customerGateway;

    public UpdateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<UpdateCustomerOutput> handle(final UpdateCustomerCommand command) {
        final var id = CustomerID.from(command.id());

        final var maybeCustomer = this.customerGateway.findById(id);
        if (maybeCustomer.isEmpty()) {
            return Result.failure(NotFoundException.with(Customer.class, id));
        }

        final var customer = maybeCustomer.get();
        final var notification = Notification.create();

        customer.update(
            command.name(),
            command.email(),
            command.phoneNumber(),
            command.address()
        );

        customer.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var updatedCustomer = this.customerGateway.update(customer);
        return Result.success(UpdateCustomerOutput.from(updatedCustomer));
    }
}
