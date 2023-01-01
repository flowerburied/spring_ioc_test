package com.example.spring_ioc_test01.controller;

import com.example.spring_ioc_test01.common.R;
import com.example.spring_ioc_test01.entity.Orders;
import com.example.spring_ioc_test01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    /**
     * 用户下单
     *
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {

        log.info("订单数据：{}", orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

}