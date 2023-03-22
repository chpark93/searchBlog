package com.ch.core.code;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Errors {

    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED"),
    REQUEST_TIMEOUT(408, "REQUEST_TIMEOUT"),
    TEMPORARILY_UNAVAILABLE(480, "TEMPORARILY_UNAVAILABLE"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    SERVICE_UNAVAILABLE(503, "SERVICE_UNAVAILABLE"),
    ;

    private final String message;
    private final int status;

    Errors(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    private static final Map<Integer, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Errors::getStatus, Errors::getMessage))
    );

    public static Errors of(final int status) {
        return Errors.valueOf(CODE_MAP.get(status));
    }


    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return status;
    }

}
