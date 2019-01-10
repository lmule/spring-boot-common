package com.magic80.springBootCommon.exception;

public class BaseBizException extends Exception {
    /**
     * 前端用的错误码，会根据这个状态码有不同的处理方式
     */
    private int code;

    /**
     * 前端用的错误信息
     */
    private String messageTip;

    public BaseBizException(int code, String messageTip) {
        this.code = code;
        this.messageTip = messageTip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageTip() {
        return messageTip;
    }

    public void setMessageTip(String messageTip) {
        this.messageTip = messageTip;
    }
}
