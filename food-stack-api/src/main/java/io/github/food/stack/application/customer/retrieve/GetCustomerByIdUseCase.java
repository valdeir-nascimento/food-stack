package io.github.food.stack.application.customer.retrieve;

import io.github.food.stack.application.QueryHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.customer.Customer;
import io.github.food.stack.domain.customer.CustomerGateway;
import io.github.food.stack.domain.customer.CustomerID;
import io.github.food.stack.domain.exception.NotFoundException;

import java.util.Objects;

public class GetCustomerByIdUseCase implements QueryHandler<GetCustomerByIdQuery, CustomerOutput> {

    private final CustomerGateway customerGateway;

    public GetCustomerByIdUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Result<CustomerOutput> handle(final GetCustomerByIdQuery query) {
        final var id = CustomerID.from(query.id());

        return this.customerGateway.findById(id)
            .map(CustomerOutput::from)
            .map(Result::success)
            .orElseGet(() -> Result.failure(NotFoundException.with(Customer.class, id)));
    }
}
