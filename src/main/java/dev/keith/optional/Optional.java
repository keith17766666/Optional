package dev.keith.optional;

import dev.keith.optional.internal.OptionalImpl;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;


public interface Optional<T> {
    default T orElseThrow() {
        if (isEmpty()) {
            throw new NoSuchElementException("No value present");
        }
        return get();
    }
    <E extends Throwable> T orElseThrow(Supplier<E> eSupplier) throws E;
    T get();
    default T orElse(T other) {
        return isPresent() ? get() : other;
    }
    default T orElseGet(Supplier<T> other) {
        return orElse(other.get());
    }
    boolean isPresent();
    default boolean isEmpty() {
        return !isPresent();
    }
    default void ifPresent(Consumer<T> action) {
        if(isPresent()) {
            action.accept(get());
        }
    }
    default void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if(isPresent()) {
            action.accept(get());
        } else {
            emptyAction.run();
        }
    }
    default Optional<T> or(Supplier<? extends Optional<? extends T>> supplier) {
        Objects.requireNonNull(supplier);
        if (isPresent()) {
            return this;
        } else {
            @SuppressWarnings("unchecked")
            Optional<T> r = (Optional<T>) supplier.get();
            return Objects.requireNonNull(r);
        }
    }
    default Stream<T> stream() {
        if (isEmpty()) {
            return Stream.empty();
        } else {
            return Stream.of(get());
        }
    }
    static <T> Optional<T> of(T value) {
        if(value != null) {
            return new OptionalImpl<>(value);
        }
        throw new NullPointerException("Null value given to method of(T value)");
    }
    static <T> Optional<T> empty() {
        return new OptionalImpl<>(null);
    }
    static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }
}
