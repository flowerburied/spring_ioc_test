package com.example.spring_ioc_test01.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.spring_ioc_test01.entity.RedisUser;
import com.example.spring_ioc_test01.service.RedisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/redisUser")
@Slf4j
public class RedisUserController {

    @Resource
    RedisUserService redisUserService;

    /**
     * CachePut：将方法返回值放入缓存
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     */
//    @CachePut(value = "userCache", key = "#result.id")
    @CachePut(value = "userCache", key = "#redisUser.id")
    @PostMapping
    public RedisUser save(RedisUser redisUser) {
        redisUserService.save(redisUser);

        log.info("redisUser:{}", redisUser);
        return redisUser;
    }

    /**
     * CacheEvict：清理指定缓存
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     */
    @CacheEvict(value = "userCache", key = "#p0")
    //@CacheEvict(value = "userCache",key = "#root.args[0]")
    //@CacheEvict(value = "userCache",key = "#id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        redisUserService.removeById(id);
    }

    //@CacheEvict(value = "userCache",key = "#p0.id")
    //@CacheEvict(value = "userCache",key = "#user.id")
    //@CacheEvict(value = "userCache",key = "#root.args[0].id")
    @CacheEvict(value = "userCache", key = "#result.id")
    @PutMapping
    public RedisUser update(RedisUser redisUser) {
        redisUserService.updateById(redisUser);
        return redisUser;
    }

    /**
     * Cacheable：在方法执行前spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放到缓存中
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     * condition：条件，满足条件时才缓存数据
     * unless：满足条件则不缓存
     */
    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    @GetMapping("/{id}")
    public RedisUser getById(@PathVariable Long id) {
        RedisUser byId = redisUserService.getById(id);
        return byId;
    }

    @Cacheable(value = "userCache", key = "#user.id + '_' + #user.name")
    @GetMapping("/list")
    public List<RedisUser> list(RedisUser redisUser) {
        LambdaQueryWrapper<RedisUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(redisUser.getId() != null, RedisUser::getId, redisUser.getId());
        queryWrapper.eq(redisUser.getName() != null, RedisUser::getName, redisUser.getName());
        List<RedisUser> list = redisUserService.list(queryWrapper);
        return list;
    }


}
