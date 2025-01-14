package com.teamdevroute.devroute.global.exception;

import com.teamdevroute.devroute.global.error.ErrorCodes;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCodes errorCodes;

    public BusinessException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
