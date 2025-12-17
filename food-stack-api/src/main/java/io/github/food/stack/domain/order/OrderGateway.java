package io.github.food.stack.domain.order;

import java.util.Optional;

public interface OrderGateway {

    Order create(Order order);

    Order update(Order order);

    Optional<Order> findById(OrderID id);

    void deleteById(OrderID id);
}
