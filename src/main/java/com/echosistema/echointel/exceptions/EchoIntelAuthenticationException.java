package com.echosistema.echointel.exceptions;

import java.util.Collections;

public class EchoIntelAuthenticationException extends EchoIntelException {

    private static final String DEFAULT_MESSAGE = "Authentication failed";
    private static final int DEFAULT_STATUS_CODE = 401;

    public EchoIntelAuthenticationException() {
        this(DEFAULT_MESSAGE, DEFAULT_STATUS_CODE, null);
    }

    public EchoIntelAuthenticationException(String message) {
        this(message, DEFAULT_STATUS_CODE, null);
    }

    public EchoIntelAuthenticationException(String message, Integer statusCode) {
        this(message, statusCode, null);
    }

    public EchoIntelAuthenticationException(String message, Integer statusCode, Throwable cause) {
        super(message, statusCode, Collections.emptyMap(), cause);
    }
}
