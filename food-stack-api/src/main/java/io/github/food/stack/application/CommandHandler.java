package io.github.food.stack.application;

import io.github.food.stack.domain.control.Result;

@FunctionalInterface
public interface CommandHandler<C, R> {

    Result<R> handle(C command);
}
