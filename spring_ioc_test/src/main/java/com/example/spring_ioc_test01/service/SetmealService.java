package com.example.spring_ioc_test01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring_ioc_test01.dto.SetmealDto;
import com.example.spring_ioc_test01.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);
}
