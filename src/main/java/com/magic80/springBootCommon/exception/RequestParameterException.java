package com.magic80.springBootCommon.exception;

public class RequestParameterException extends BaseBizException {

    public RequestParameterException(String message) {
        super(-1, message);
    }
}
