package com.bvn13.jmonad;

import com.bvn13.jmonad.exceptions.ValueNotFoundException;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by bvn13 on 10.06.2019.
 */
public class Success<T> extends Try<T> {

    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public T orElse(T value) {
        return this.value;
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        return Try.successful(f.apply(value));
    }

    @Override
    public Try<T> filter(Function<? super T, ? extends Boolean> f) {
        Objects.requireNonNull(f);
        if (f.apply(value)) {
            return Try.successful(value);
        } else {
            return Try.failure(new ValueNotFoundException("not found"));
        }
    }

    @Override
    public Try<T> map(Function<? super T, ? extends T> f) {
        Objects.requireNonNull(f);
        return Try.successful(f.apply(value));
    }

    @Override
    public T orElseThrow(Throwable e) throws Throwable {
        return value;
    }

    @Override
    public T orElseThrow() throws Throwable {
        return value;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
