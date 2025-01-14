package com.teamdevroute.devroute.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private List<String> values = new ArrayList<>();

    private ErrorResponse(final int status, final ErrorCodes code) {
        this.status = status;
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public ErrorResponse(int status, ErrorCodes code, String value) {
        this(status, code);
        this.values = List.of(value);
    }

    public ErrorResponse(int status, ErrorCodes code, final List<String> values) {
        this(status, code);
        this.values = values;
    }

    public static ErrorResponse of(final int status, final ErrorCodes code, final BindingResult bindingResult) {
        List<String> values = bindingResult.getFieldErrors().stream()
                .map(error ->
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
                .toList();
        return new ErrorResponse(status, code, values);
    }

    public static ErrorResponse of(final int status, final ErrorCodes code) {
        return new ErrorResponse(status, code);
    }

    public static ErrorResponse of(final int status, final ErrorCodes code, final String value) {
        return new ErrorResponse(status, code, value);
    }
}
