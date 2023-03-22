package com.ch.core.response.common;

public class RestResultBuilder {
    private String code;

    private String message;

    private Object data;

    public RestResultBuilder code(String code) {
        this.code = code;
        return this;
    }

    public RestResultBuilder message(String message) {
        this.message = message;
        return this;
    }

    public RestResultBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public RestResult build() {
        RestResult RestResultInfo = new RestResult();
        RestResultInfo.setCode(this.code);
        RestResultInfo.setMessage(this.message);
        RestResultInfo.setData(this.data);

        return RestResultInfo;
    }
}

