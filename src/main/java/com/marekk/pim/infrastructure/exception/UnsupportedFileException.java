package com.marekk.pim.infrastructure.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "wrong file extension")
class UnsupportedFileException extends PIMException {
}
