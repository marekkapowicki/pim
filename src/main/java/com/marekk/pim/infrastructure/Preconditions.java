package com.marekk.pim.infrastructure;

import com.marekk.pim.infrastructure.exception.PIMException;

import java.util.function.Supplier;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression, Supplier<PIMException> exceptionSupplier) {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }
}
