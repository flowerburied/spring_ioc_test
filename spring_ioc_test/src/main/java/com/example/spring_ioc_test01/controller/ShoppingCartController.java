package com.example.spring_ioc_test01.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.spring_ioc_test01.common.BaseContext;
import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.entity.ShoppingCart;
import com.example.spring_ioc_test01.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Resource
    ShoppingCartService shoppingCartService;

    /**
     * 添加 购物车
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {

        log.info("shopping:{}", shoppingCart);
//        设置用户id,指定当前是哪个用户的购物车数据

        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
//        查询当前菜品或套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);

        if (dishId != null) {
//            添加的是菜品
        } else {
//            添加到购物车的是套餐
        }
//        如果存在,就在原来数量上加一

//        如果不存在,则添加到购物车,数量默认就是一

        return null;
    }

    public R<List<ShoppingCart>> list() {

        return null;
    }
}
