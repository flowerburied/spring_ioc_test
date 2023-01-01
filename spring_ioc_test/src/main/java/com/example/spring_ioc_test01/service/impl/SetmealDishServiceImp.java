package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.dto.SetmealDto;
import com.example.spring_ioc_test01.entity.SetmealDish;
import com.example.spring_ioc_test01.mapper.SetmealDishMapper;

import com.example.spring_ioc_test01.service.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealDishServiceImp extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {


}
