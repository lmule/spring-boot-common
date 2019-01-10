package com.magic80.springbootcommon.helper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.magic80.springbootcommon.helper.page.PagedMutipleTableCountFunction;
import com.magic80.springbootcommon.helper.page.PagedMutipleTableFunction;
import com.magic80.springbootcommon.helper.page.PagedSingleTableFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PagedHelper {

    public static Map<String, Object> pagedList(Map<String, String> requestMap,
                                                PagedSingleTableFunction singleTableFunction) {
        PagedParameterParser parameterParser = new PagedParameterParser(requestMap);
        Page page = PageHelper.startPage(parameterParser.getPageIndex(), parameterParser.getPageSize(), true);
        List list = singleTableFunction.call(requestMap);
        return formatPagedList(
                page.getTotal(),
                list
        );
    }

    public static Map<String, Object> pagedList(Map<String, String> requestMap,
                                                PagedMutipleTableFunction pagedMutipleTableFunction,
                                                PagedMutipleTableCountFunction pagedMutipleTableCountFunction) {
        PagedParameterParser parameterParser = new PagedParameterParser(requestMap);
        return formatPagedList(
                pagedMutipleTableCountFunction.call(requestMap),
                pagedMutipleTableFunction.call(
                        (parameterParser.getPageIndex() - 1) * parameterParser.getPageSize(),
                        parameterParser.getPageSize(),
                        requestMap
                )
        );
    }

    private static Map<String, Object> formatPagedList(Long total, List list) {
        return new HashMap<String, Object>(){
            {
                put("total", total);
                put("list", list);
            }
        };
    }

    private static class PagedParameterParser{

        private Map<String, String> requestMap;

        public PagedParameterParser(Map<String, String> requestMap) {
            this.requestMap = requestMap;
        }

        /**
         * 起始页码从1开始
         * @return
         */
        public Integer getPageIndex() {
            return parse("pageIndex", 1);
        }

        public Integer getPageSize() {
            return parse("pageSize", 10);
        }

        private Integer parse(String key, Integer defaultValue) {
            return Optional
                    .ofNullable(requestMap.get(key))
                    .map(Integer::parseInt)
                    .orElse(defaultValue);
        }
    }

}
