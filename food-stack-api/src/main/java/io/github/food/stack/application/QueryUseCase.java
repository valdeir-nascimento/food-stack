package io.github.food.stack.application;

import io.github.food.stack.domain.control.Result;

@FunctionalInterface
public interface QueryUseCase<Q, R> {

    Result<R> execute(Q query);
}
