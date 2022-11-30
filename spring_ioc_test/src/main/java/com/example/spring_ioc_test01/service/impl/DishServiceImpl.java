package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.Dish;
import com.example.spring_ioc_test01.mapper.DishMapper;
import com.example.spring_ioc_test01.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
