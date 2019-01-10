package com.magic80.springBootCommon.helper.page;

import java.util.Map;

@FunctionalInterface
public interface PagedMutipleTableCountFunction {
    Long call(Map<String, String> requestMap);
}
