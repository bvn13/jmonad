package com.bvn13.jmonad;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by bvn13 on 10.06.2019.
 */
public class Failure<T> extends Try<T> {

    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public T get() throws Throwable {
        throw e;
    }

    @Override
    public T orElse(T value) {
        return value;
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public Try<T> filter(Function<? super T, ? extends Boolean> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public Try<T> map(Function<? super T, ? extends T> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public T orElseThrow(Throwable e) throws Throwable {
        Objects.requireNonNull(e);
        throw e;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
