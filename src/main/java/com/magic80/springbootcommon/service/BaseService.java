package com.magic80.springbootcommon.service;

import com.magic80.springbootcommon.dao.BaseDao;
import com.magic80.springbootcommon.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseService<D extends BaseDao, E extends BaseEntity> {

    @Autowired
    protected D dao;

    private Class<E> entityClass;

    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<E>) pt.getActualTypeArguments()[1];
    }

    public void insert(E entity) {
        dao.insertSelective(entity);
    }

    public void updateById(E entity) {
        dao.updateByPrimaryKeySelective(entity);
    }

    public void deleteById(Integer id) {
        dao.deleteByPrimaryKey(id);
    }

    public E findById(Integer id) {
        return (E) dao.selectByPrimaryKey(id);
    }

    public List<E> findAll() {
        Example example = new Example(entityClass);
        example.setOrderByClause("id desc");
        return dao.selectByExample(example);
    }
}
