package com.ch.core.response.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestResult {
    private String code;

    private String message;

    private Object data;

    public static RestResultBuilder builder() {
        return new RestResultBuilder();
    }

    public static RestSuccessResultBuilder successBuilder() {
        return new RestSuccessResultBuilder();
    }

    public static RestResult successBuilder(Object data) {
        return new RestSuccessResultBuilder(data).build();
    }
}