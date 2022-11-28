package com.example.spring_ioc_test01.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置MP的分页插件
 */
@Configuration   //配置类
public class MybatisPlusConfig {

    @Bean  //spring管理
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

//添加拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return mybatisPlusInterceptor;
    }
}
