package io.github.food.stack.application.customer.delete;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.customer.CustomerID;

import java.util.Objects;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCaseImpl(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<Void> execute(final DeleteCustomerCommand command) {
        this.customerGateway.deleteById(CustomerID.from(command.id()));
        return Result.success(null);
    }
}
