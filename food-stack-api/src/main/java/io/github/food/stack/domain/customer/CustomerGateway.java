package io.github.food.stack.domain.customer;

import java.util.Optional;

public interface CustomerGateway {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Optional<Customer> findById(CustomerID id);

    void deleteById(CustomerID id);
}
