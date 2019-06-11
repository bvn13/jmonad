package com.bvn13.jmonad;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Full implementation of Try monad
 * @see <a href="https://medium.com/swlh/write-a-monad-in-java-seriously-50a9047c9839">Source article</a>
 *
 * Created by bvn13 on 10.06.2019.
 */
public abstract class Try<T> {

    public static <U> Try<U> ofThrowable(Supplier<U> f) {
        Objects.requireNonNull(f);
        try {
            return Try.successful(f.get());
        } catch (Throwable e) {
            return Try.failure(e);
        }
    }

    public static <U> Success<U> successful(U u) {
        return new Success<>(u);
    }

    public static <U> Failure<U> failure(Throwable e) {
        return new Failure<>(e);
    }

    public abstract T get() throws Throwable;

    public abstract T orElse(T value);

    public abstract <U> Try<U> flatMap(Function<? super T, ? extends U> f);

    public abstract Try<T> filter(Function<? super T, ? extends Boolean> f);

    public abstract Try<T> map(Function<? super T, ? extends T> f);

    public abstract T orElseThrow(Throwable e) throws Throwable;

    public abstract Optional<T> toOptional();

    public abstract boolean isSuccess();

}
