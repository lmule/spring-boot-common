package com.magic80.springbootcommon.dao;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 扩展通用Mapper
 * @param <E>
 */
@RegisterMapper
public interface BaseDao<E> extends Mapper<E> {
}
