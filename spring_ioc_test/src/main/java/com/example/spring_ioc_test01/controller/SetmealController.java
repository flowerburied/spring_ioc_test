package com.example.spring_ioc_test01.controller;


//套餐管理

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.dto.SetmealDto;
import com.example.spring_ioc_test01.entity.Category;
import com.example.spring_ioc_test01.entity.Setmeal;
import com.example.spring_ioc_test01.entity.SetmealDish;
import com.example.spring_ioc_test01.service.CategoryService;
import com.example.spring_ioc_test01.service.SetmealDishService;
import com.example.spring_ioc_test01.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {


    @Resource
    SetmealService setmealService;

    @Resource
    SetmealDishService setmealDishService;

    @Resource
    CategoryService categoryService;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {

        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {


        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, queryWrapper);

//        log.info("pageInfo={}", pageInfo);
//对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records"); //pageInfo拷贝到dtoPage
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
//根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null) {
//                分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;

        }).collect(Collectors.toList());


//        List<SetmealDto> list = null;

        dtoPage.setRecords(list);

        return R.success(dtoPage);
    }
}
