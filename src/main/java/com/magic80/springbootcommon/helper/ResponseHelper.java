package com.magic80.springbootcommon.helper;

import com.magic80.springbootcommon.exception.BaseBizException;
import com.magic80.springbootcommon.vo.ResponseVO;

public class ResponseHelper {

    public static ResponseVO renderSuccess(Object data) {
        return new ResponseVO(data);
    }

    public static ResponseVO renderException(BaseBizException ex) {
        ex.printStackTrace();
        return new ResponseVO(ex.getCode(), "", ex.getMessageTip());
    }
}
