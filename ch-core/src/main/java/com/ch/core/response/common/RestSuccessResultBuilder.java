package com.ch.core.response.common;

public class RestSuccessResultBuilder {

    private String code;

    private String message = "SUCCESS";

    private Object data;

    public RestSuccessResultBuilder() {

    }

    public RestSuccessResultBuilder(Object data) {
        this.data = data;
    }

    public RestSuccessResultBuilder code(String code) {
        this.code = code;
        return this;
    }

    public RestSuccessResultBuilder message(String message) {
        this.message = message;
        return this;
    }

    public RestSuccessResultBuilder data(Object data) {
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

