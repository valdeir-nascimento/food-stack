package io.github.food.stack.application.customer.retrieve;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.Customer;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.customer.CustomerID;
import io.github.food.stack.domain.exception.NotFoundException;

import java.util.Objects;

public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {

    private final CustomerGateway customerGateway;

    public GetCustomerByIdUseCaseImpl(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<CustomerOutput> execute(final GetCustomerByIdQuery query) {
        final var id = CustomerID.from(query.id());

        return this.customerGateway.findById(id)
                .map(CustomerOutput::from)
                .map(Result::success)
                .orElseGet(() -> Result.failure(NotFoundException.with(Customer.class, id)));
    }
}
