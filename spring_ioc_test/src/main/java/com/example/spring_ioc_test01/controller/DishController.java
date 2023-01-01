package com.example.spring_ioc_test01.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.dto.DishDto;
import com.example.spring_ioc_test01.entity.Category;
import com.example.spring_ioc_test01.entity.Dish;
import com.example.spring_ioc_test01.entity.DishFlavor;
import com.example.spring_ioc_test01.service.CategoryService;
import com.example.spring_ioc_test01.service.DishFlavorService;
import com.example.spring_ioc_test01.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Resource
    DishService dishService;

    @Resource
    DishFlavorService dishFlavorService;

    @Resource
    CategoryService categoryService;

    /**
     * 新增菜品
     *
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }


    /**
     * 菜品信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
//        构造分页构造器
        Page<Dish> pageInfo = new Page(page, pageSize);

        Page<DishDto> dishDtoPage = new Page<>();  //创建前端需要的类
//        构建条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        添加过滤条件
        queryWrapper.like(name != null, Dish::getName, name);
//        添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);
//        执行分页查询
        dishService.page(pageInfo, queryWrapper);
//        对象拷贝
//        records 忽略参数不拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

//        处理数据集
        List<Dish> records = pageInfo.getRecords();

        log.info("records", records);
        List<DishDto> list = records.stream().map((item) -> {

            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();  //先获取分类ID
//            根据id分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            return dishDto;
        }).collect(Collectors.toList());


        dishDtoPage.setRecords(list);


        return R.success(dishDtoPage);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id) {

        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * 修改菜品
     *
     * @return
     */
    @PutMapping
    public R<String> updata(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }

    /**
     * 根据条件查询对应的菜品数据
     *
     * @param dish
     * @return
     */
//    @GetMapping("/list")
//    public R<List<Dish>> list(Dish dish) {
////构造查询条件
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
//
////        添加条件，查询状态为1的
//        queryWrapper.eq(Dish::getStatus, 1);
////添加排序条件
//        queryWrapper.orderByAsc(Dish::getSort).orderByAsc(Dish::getUpdateTime);
//
//        List<Dish> list = dishService.list(queryWrapper);
//
//
//        return R.success(list);
//    }
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
//构造查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());

//        添加条件，查询状态为1的
        queryWrapper.eq(Dish::getStatus, 1);
//添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByAsc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);
        List<DishDto> dishDtoList = list.stream().map((item) -> {

            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();  //先获取分类ID
//            根据id分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            //当前菜品id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
//            SQL:select * from dish_flovor where dish_id =?
            List<DishFlavor> dishFlavorsList = dishFlavorService.list(lambdaQueryWrapper);

            dishDto.setFlavors(dishFlavorsList);
            return dishDto;
        }).collect(Collectors.toList());

        return R.success(dishDtoList);
    }


}
