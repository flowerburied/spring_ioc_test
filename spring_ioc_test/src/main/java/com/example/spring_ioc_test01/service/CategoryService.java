package com.example.spring_ioc_test01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring_ioc_test01.entity.Category;

public interface CategoryService extends IService<Category> {

    /**
     * 新写逻辑删除
     */
    public void remove(Long ids);

}
