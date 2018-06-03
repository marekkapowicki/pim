package com.marekk.pim.domain.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such resource")
@NoArgsConstructor
class ResourceNotFound extends PIMException {
    ResourceNotFound(String message) {
        super(message);
    }
}
