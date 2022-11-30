package com.example.spring_ioc_test01.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.entity.Category;
import com.example.spring_ioc_test01.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
//        构造分页构造器
        Page pageInfo = new Page(page, pageSize);
//        构造条件构造器
        LambdaQueryWrapper<Category> queryWapper = new LambdaQueryWrapper<>();
//        添加排序条件
        queryWapper.orderByDesc(Category::getSort);
//执行查询
        categoryService.page(pageInfo, queryWapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id删除分类
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids) {

        log.info("删除分类，id为：{}", ids);

        categoryService.removeById(ids);

        return R.success("分类信息删除成功");
    }


}
