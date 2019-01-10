package com.magic80.springBootCommon.controller;

import com.magic80.springBootCommon.helper.PagedHelper;
import com.magic80.springBootCommon.model.BaseEntity;
import com.magic80.springBootCommon.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public class BaseController<S extends BaseService, E extends BaseEntity> {

    @Autowired
    protected S service;

    @PostMapping("/insert")
    public void insert(@RequestBody E entity) {
        service.insert(entity);
    }

    @PostMapping("/update")
    public void updateById(@RequestBody E entity) {
        service.updateById(entity);
    }

    @GetMapping("/delete")
    public void deleteById(Integer id) {
        service.deleteById(id);
    }

    @GetMapping("/get")
    public E findById(Integer id) {
        return (E) service.findById(id);
    }

    @GetMapping("/all")
    public List<E> findAll() {
        return service.findAll();
    }

    @GetMapping("/list")
    public Map<String, Object> findList(@RequestParam Map<String, String> requestMap) {
        return PagedHelper.pagedList(requestMap, map -> findDefaultList(map));
    }

    protected List<E> findDefaultList(Map<String, String> requestMap) {
        return null;
    }
}
