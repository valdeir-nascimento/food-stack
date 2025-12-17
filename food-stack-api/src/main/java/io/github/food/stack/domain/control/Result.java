package io.github.food.stack.domain.control;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed abstract class Result<T> permits Result.Success, Result.Failure {

    private Result() {
    }

    public static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    public static <T> Result<T> failure(Throwable error) {
        return new Failure<>(error);
    }

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract T get();

    public abstract Throwable getError();

    public <U> Result<U> map(Function<? super T, ? extends U> mapper) {
        if (isSuccess()) {
            return success(mapper.apply(get()));
        } else {
            return failure(getError());
        }
    }

    public <U> Result<U> flatMap(Function<? super T, Result<U>> mapper) {
        if (isSuccess()) {
            return mapper.apply(get());
        } else {
            return failure(getError());
        }
    }

    public T getOrElse(Supplier<T> supplier) {
        if (isSuccess()) {
            return get();
        } else {
            return supplier.get();
        }
    }

    public T getOrElseThrow(Function<Throwable, RuntimeException> exceptionSupplier) {
        if (isSuccess()) {
            return get();
        } else {
            throw exceptionSupplier.apply(getError());
        }
    }

    public Optional<T> toOptional() {
        if (isSuccess()) {
            return Optional.ofNullable(get());
        } else {
            return Optional.empty();
        }
    }

    public static final class Success<T> extends Result<T> {
        private final T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Throwable getError() {
            throw new IllegalStateException("Called getError on Success");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Success<?> success = (Success<?>) o;
            return Objects.equals(value, success.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static final class Failure<T> extends Result<T> {
        private final Throwable error;

        private Failure(Throwable error) {
            this.error = Objects.requireNonNull(error);
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public T get() {
            throw new IllegalStateException("Called get on Failure");
        }

        @Override
        public Throwable getError() {
            return error;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Failure<?> failure = (Failure<?>) o;
            return Objects.equals(error, failure.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(error);
        }
    }
}
