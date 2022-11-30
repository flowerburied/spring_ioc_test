package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.common.CustomException;
import com.example.spring_ioc_test01.entity.Category;
import com.example.spring_ioc_test01.entity.Dish;
import com.example.spring_ioc_test01.entity.Setmeal;
import com.example.spring_ioc_test01.mapper.CategoryMapper;
import com.example.spring_ioc_test01.service.CategoryService;
import com.example.spring_ioc_test01.service.DishService;
import com.example.spring_ioc_test01.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;

//实现类 合并mapper和service
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Resource
    DishService dishService;

    @Resource
    SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {

//        添加dish构造器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//添加查询条件，根据分类id进行查询

        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);


//        查询当前分类是否关联了菜单，如果已经关联，抛出一个业务异常
        if (count1 > 0) {
//    已经关联菜品，抛出一个业务异常
//            自定义抛出异常
            throw new CustomException("当前分类下关联了菜品，不能删除");

        }
//        查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            //    已经关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }


//正常删除分类
        super.removeById(id);


    }

}
