package com.echosistema.echointel.exceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class EchoIntelException extends RuntimeException {

    private final Integer statusCode;
    private final Map<String, Object> context;

    public EchoIntelException(String message) {
        this(message, null, Collections.emptyMap(), null);
    }

    public EchoIntelException(String message, Integer statusCode) {
        this(message, statusCode, Collections.emptyMap(), null);
    }

    public EchoIntelException(String message, Integer statusCode, Map<String, Object> context) {
        this(message, statusCode, context, null);
    }

    public EchoIntelException(String message, Integer statusCode, Map<String, Object> context, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.context = context != null ? context : Collections.emptyMap();
    }
}
