package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.dto.SetmealDto;
import com.example.spring_ioc_test01.entity.Setmeal;
import com.example.spring_ioc_test01.entity.SetmealDish;
import com.example.spring_ioc_test01.mapper.SetmealMapper;
import com.example.spring_ioc_test01.service.SetmealDishService;
import com.example.spring_ioc_test01.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService; //导入菜品表

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    @Transactional  //控制两张表时添加保证一致性
    public void saveWithDish(SetmealDto setmealDto) {
//        保存套餐信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
//        保存套餐和菜品的相关信息，操作setmeal_dish,执行insert操作

        setmealDishService.saveBatch(setmealDishes);

    }
}
