package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.ShoppingCart;
import com.example.spring_ioc_test01.mapper.ShoppingCartMapper;
import com.example.spring_ioc_test01.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImp extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
