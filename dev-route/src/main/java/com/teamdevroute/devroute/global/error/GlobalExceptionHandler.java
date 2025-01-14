package com.teamdevroute.devroute.global.error;

import com.github.dockerjava.api.exception.UnauthorizedException;
import com.teamdevroute.devroute.global.error.exception.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException e) {
        final ErrorCodes errorCodes = e.getErrorCodes();
        final String value = e.getValue();
        final int status = HttpStatus.CONFLICT.value();

        final ErrorResponse errorResponse = ErrorResponse.of(status, errorCodes, value);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        final ErrorCodes errorCodes = e.getErrorCodes();
        final int status = HttpStatus.BAD_GATEWAY.value();

        final ErrorResponse errorResponse = ErrorResponse.of(status, errorCodes);

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();;

        final ErrorResponse response = ErrorResponse.of(status, ErrorCodes.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
