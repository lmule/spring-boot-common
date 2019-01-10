package com.magic80.springBootCommon.helper;

import com.magic80.springBootCommon.exception.BaseBizException;
import com.magic80.springBootCommon.vo.ResponseVO;

public class ResponseHelper {

    public static ResponseVO renderSuccess(Object data) {
        return new ResponseVO(data);
    }

    public static ResponseVO renderException(BaseBizException ex) {
        ex.printStackTrace();
        return new ResponseVO(ex.getCode(), "", ex.getMessageTip());
    }
}
