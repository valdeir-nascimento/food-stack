package io.github.food.stack.domain.order;

public interface OrderGateway {

    Order create(Order order);

    Order update(Order order);

    Order findById(OrderID id);

    void deleteById(OrderID id);
}
