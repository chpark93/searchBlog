package com.ch.core.exception;

import com.ch.core.code.Errors;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serial;

/**
 * CommonAccessException
 */
public class CommonAccessException extends RuntimeException {

    private final Errors errorConstants;

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    public CommonAccessException(String message, Errors errorConstants) {
        super(message);
        this.errorConstants = errorConstants;
    }

    public CommonAccessException(Errors errorConstants) {
        super(errorConstants.getMessage());
        this.errorConstants = errorConstants;
    }

    public Errors getErrorCode() {
        return errorConstants;
    }
}