package com.example.demo.toggle.functional;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T, R> {

    R apply(T t) throws Throwable;

    default Function<T, R> unchecked() {
        return t1 -> {
            try {
                return apply(t1);
            } catch(Throwable t) {
                return sneakyThrow(t);
            }
        };
    }

    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }

}
