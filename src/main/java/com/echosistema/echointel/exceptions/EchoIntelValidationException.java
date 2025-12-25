package com.echosistema.echointel.exceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class EchoIntelValidationException extends EchoIntelException {

    private static final String DEFAULT_MESSAGE = "Validation failed";
    private static final int DEFAULT_STATUS_CODE = 422;

    private final List<Object> errors;

    public EchoIntelValidationException() {
        this(DEFAULT_MESSAGE, Collections.emptyList(), null);
    }

    public EchoIntelValidationException(String message) {
        this(message, Collections.emptyList(), null);
    }

    public EchoIntelValidationException(String message, List<Object> errors) {
        this(message, errors, null);
    }

    public EchoIntelValidationException(String message, List<Object> errors, Throwable cause) {
        super(message, DEFAULT_STATUS_CODE, Map.of("errors", errors != null ? errors : Collections.emptyList()), cause);
        this.errors = errors != null ? errors : Collections.emptyList();
    }
}
