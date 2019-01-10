package com.magic80.springbootcommon.helper.page;

import com.magic80.springbootcommon.model.BaseEntity;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface PagedMutipleTableFunction {
    List<? extends BaseEntity> call(Integer pageIndex, Integer pageSize, Map<String, String> requestMap);
}
