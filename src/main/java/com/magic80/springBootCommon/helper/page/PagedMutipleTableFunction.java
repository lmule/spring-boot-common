package com.magic80.springBootCommon.helper.page;

import com.magic80.springBootCommon.model.BaseEntity;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PagedMutipleTableFunction {
    List<? extends BaseEntity> call(Integer pageIndex, Integer pageSize, Map<String, String> requestMap);
}
