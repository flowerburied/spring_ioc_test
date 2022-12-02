package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.dto.DishDto;
import com.example.spring_ioc_test01.entity.Dish;
import com.example.spring_ioc_test01.entity.DishFlavor;
import com.example.spring_ioc_test01.mapper.DishMapper;
import com.example.spring_ioc_test01.service.DishFlavorService;
import com.example.spring_ioc_test01.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    //实现方法

    /**
     * 新增菜品，同时保存对应的口味数据
     *
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

//        保存菜品的基本信息到菜品表dish

        this.save(dishDto);

        Long dishId = dishDto.getId(); //菜品id

        //处理菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
//        .collect(Collectors.toList())  重新转成list数据
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId); //赋值dishid
            return item;
        }).collect(Collectors.toList());


//保存菜品口味到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);

    }
}
