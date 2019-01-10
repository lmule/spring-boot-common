package com.magic80.springBootCommon.helper.page;

import com.magic80.springBootCommon.model.BaseEntity;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PagedSingleTableFunction {
    List<? extends BaseEntity> call(Map<String, String> requestMap);
}
