package com.marekk.pim.domain.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "illegal state detected")
@NoArgsConstructor
class IllegalState extends PIMException {
    IllegalState(String message) {
        super(message);
    }
}
