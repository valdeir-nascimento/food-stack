package io.github.food.stack.application.order.retrieve;

public record GetOrderByIdQuery(String id) {

    public static GetOrderByIdQuery with(final String id) {
        return new GetOrderByIdQuery(id);
    }
}
