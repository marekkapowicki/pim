package com.marekk.pim.infrastructure.exception;

import lombok.NoArgsConstructor;

import java.util.function.Supplier;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Exceptions {
    public static Supplier<PIMException> notFound() {
        return ResourceNotFound::new;
    }

    public static Supplier<PIMException> notFound(String message) {
        return () -> new ResourceNotFound(message);
    }

    public static Supplier<PIMException> illegalState() {
        return IllegalState::new;
    }

    public static Supplier<PIMException> illegalState(String message) {
        return () -> new IllegalState(message);
    }

    public static Supplier<PIMException> conflicted() {
        return ResourceConflicted::new;
    }

    public static Supplier<PIMException> conflicted(String message) {
        return () -> new ResourceConflicted(message);
    }
}
