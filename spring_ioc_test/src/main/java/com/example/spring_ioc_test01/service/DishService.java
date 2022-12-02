package com.example.spring_ioc_test01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring_ioc_test01.dto.DishDto;
import com.example.spring_ioc_test01.entity.Dish;

public interface DishService extends IService<Dish> {

//    新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish   dish_flavor
//    定义方法后到impl中实现方法
    public void saveWithFlavor(DishDto dishDto);


}
