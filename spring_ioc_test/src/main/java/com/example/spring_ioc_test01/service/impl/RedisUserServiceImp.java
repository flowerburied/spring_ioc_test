package com.example.spring_ioc_test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_ioc_test01.entity.RedisUser;
import com.example.spring_ioc_test01.mapper.RedisUserMapper;
import com.example.spring_ioc_test01.service.RedisUserService;
import org.springframework.stereotype.Service;

@Service
public class RedisUserServiceImp extends ServiceImpl<RedisUserMapper, RedisUser> implements RedisUserService {

}
