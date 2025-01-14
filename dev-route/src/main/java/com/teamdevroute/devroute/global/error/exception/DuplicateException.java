package com.teamdevroute.devroute.global.error.exception;

import com.teamdevroute.devroute.global.error.ErrorCodes;
import lombok.Getter;

@Getter
public class DuplicateException extends BusinessException {

    private final String value;

    public DuplicateException(String value) {
        super(value, ErrorCodes.DUPLICATE);
        this.value = value;
    }

    public DuplicateException(String value, ErrorCodes errorCodes) {
        super(value, errorCodes);
        this.value = value;
    }
}
