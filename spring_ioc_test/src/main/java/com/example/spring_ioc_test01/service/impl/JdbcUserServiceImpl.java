package com.example.spring_ioc_test01.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.JdbcUser;
import com.example.spring_ioc_test01.mapper.JdbcUserMapper;
import com.example.spring_ioc_test01.service.JdbcUserService;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserServiceImpl extends ServiceImpl<JdbcUserMapper, JdbcUser> implements JdbcUserService {
}
