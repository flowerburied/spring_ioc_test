package com.example.spring_ioc_test01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan //启动拦截
@EnableTransactionManagement //开启Transactional支持 用于多表新增
@EnableCaching  //spring cache 支持
public class SpringIocTest01Application {

    public static void main(String[] args) {

        SpringApplication.run(SpringIocTest01Application.class, args);

        log.info("项目启动成功");
    }

}
