package com.magic80.springbootcommon.helper.page;

import java.util.Map;

@FunctionalInterface
public interface PagedMutipleTableCountFunction {
    Long call(Map<String, String> requestMap);
}
