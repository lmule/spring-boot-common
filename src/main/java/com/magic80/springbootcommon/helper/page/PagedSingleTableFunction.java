package com.magic80.springbootcommon.helper.page;

import com.magic80.springbootcommon.model.BaseEntity;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PagedSingleTableFunction {
    List<? extends BaseEntity> call(Map<String, String> requestMap);
}
