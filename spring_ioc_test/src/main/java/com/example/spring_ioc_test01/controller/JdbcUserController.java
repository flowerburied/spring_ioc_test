package com.example.spring_ioc_test01.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.spring_ioc_test01.entity.JdbcUser;
import com.example.spring_ioc_test01.entity.RedisUser;
import com.example.spring_ioc_test01.service.JdbcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/jdbcUser")
@Slf4j
public class JdbcUserController {

    @Resource
    JdbcUserService jdbcUserService;

    @Resource
    DataSource dataSource;

    /**
     * CachePut：将方法返回值放入缓存
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     */
//    @CachePut(value = "userCache", key = "#result.id")
//    @CachePut(value = "userCache", key = "#jdbcUser.id")
    @PostMapping
    public JdbcUser save(JdbcUser jdbcUser) {
        jdbcUserService.save(jdbcUser);

        log.info("jdbcUser:{}", jdbcUser);
        return jdbcUser;
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
        jdbcUserService.removeById(id);
    }

    //@CacheEvict(value = "userCache",key = "#p0.id")
    //@CacheEvict(value = "userCache",key = "#user.id")
    //@CacheEvict(value = "userCache",key = "#root.args[0].id")
    @CacheEvict(value = "userCache", key = "#result.id")
    @PutMapping
    public JdbcUser update(JdbcUser jdbcUser) {
        jdbcUserService.updateById(jdbcUser);
        return jdbcUser;
    }

    /**
     * Cacheable：在方法执行前spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放到缓存中
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     * condition：条件，满足条件时才缓存数据
     * unless：满足条件则不缓存
     */
//    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    @GetMapping("/{id}")
    public JdbcUser getById(@PathVariable Long id) {
        JdbcUser byId = jdbcUserService.getById(id);
        return byId;
    }

    @Cacheable(value = "userCache", key = "#user.id + '_' + #user.name")
    @GetMapping("/list")
    public List<JdbcUser> list(JdbcUser jdbcUser) {
        LambdaQueryWrapper<JdbcUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(jdbcUser.getId() != null, JdbcUser::getId, jdbcUser.getId());
        queryWrapper.eq(jdbcUser.getName() != null, JdbcUser::getName, jdbcUser.getName());
        List<JdbcUser> list = jdbcUserService.list(queryWrapper);
        return list;
    }


}
