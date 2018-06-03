package com.marekk.pim.domain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PIMException extends RuntimeException {
    PIMException(String message) {
        super(message);
    }
}
