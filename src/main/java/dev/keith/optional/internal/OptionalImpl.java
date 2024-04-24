package dev.keith.optional.internal;

import dev.keith.optional.Optional;

import java.util.function.Supplier;

public class OptionalImpl<T> implements Optional<T> {
    private final T value;
    public OptionalImpl(T value) {
        this.value = value;
    }
    @Override
    public <E extends Throwable> T orElseThrow(Supplier<E> eSupplier) throws E {
        if(this.isEmpty()) {
            throw eSupplier.get();
        } else {
            return value;
        }
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean isPresent() {
        return value != null;
    }
}
