package com.magic80.springbootcommon.model;

import com.magic80.springbootcommon.annotation.AutoCreateAnnotation;
import com.magic80.springbootcommon.annotation.AutoUpdateAnnotation;

import javax.persistence.Id;
import java.io.Serializable;

public class BaseEntity implements Serializable {

    @Id
    private Integer id;

    @AutoCreateAnnotation
    private Integer createdAt;

    @AutoUpdateAnnotation
    private Integer updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
}
