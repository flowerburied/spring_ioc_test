package com.example.spring_ioc_test01.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.spring_ioc_test01.entity.Orders;

public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
