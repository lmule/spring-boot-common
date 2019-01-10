package com.magic80.springbootcommon.exception;

public class RequestParameterException extends BaseBizException {

    public RequestParameterException(String message) {
        super(-1, message);
    }
}
