package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.Category;
import com.example.spring_ioc_test01.mapper.CategoryMapper;
import com.example.spring_ioc_test01.service.CategoryService;
import org.springframework.stereotype.Service;

//实现类 合并mapper和service
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
