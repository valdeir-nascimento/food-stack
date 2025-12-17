package io.github.food.stack.application;

import io.github.food.stack.domain.control.Result;

@FunctionalInterface
public interface QueryHandler<Q, R> {

    Result<R> handle(Q query);
}
