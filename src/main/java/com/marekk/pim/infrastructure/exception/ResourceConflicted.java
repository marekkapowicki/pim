package com.marekk.pim.infrastructure.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "resource already existed")
@NoArgsConstructor
class ResourceConflicted extends PIMException {
    ResourceConflicted(String message) {
        super(message);
    }
}
