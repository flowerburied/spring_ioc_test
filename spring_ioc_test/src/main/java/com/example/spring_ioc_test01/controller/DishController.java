package com.example.spring_ioc_test01.controller;


import com.example.spring_ioc_test01.service.DishFlavorService;
import com.example.spring_ioc_test01.service.DishService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    DishService dishService;

    @Resource
    DishFlavorService dishFlavorService;


}
