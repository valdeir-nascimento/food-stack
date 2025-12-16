package io.github.food.stack.domain.customer;

public interface CustomerGateway {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findById(CustomerID id);

    void deleteById(CustomerID id);
}
