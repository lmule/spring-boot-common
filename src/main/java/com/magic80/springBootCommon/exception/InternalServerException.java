package com.magic80.springBootCommon.exception;

public class InternalServerException extends BaseBizException {

    public InternalServerException(Exception e) {
        super(-2, e.getMessage() == null ? "服务器发生异常" : e.getMessage());
    }
}
