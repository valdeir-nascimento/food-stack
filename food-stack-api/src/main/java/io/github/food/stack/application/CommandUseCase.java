package io.github.food.stack.application;

import io.github.food.stack.domain.control.Result;

@FunctionalInterface
public interface CommandUseCase<C, R> {

    Result<R> execute(C command);
}
