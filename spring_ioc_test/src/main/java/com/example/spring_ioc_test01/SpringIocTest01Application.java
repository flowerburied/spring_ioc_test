package com.example.spring_ioc_test01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan //启动拦截
public class SpringIocTest01Application {

    public static void main(String[] args) {

        SpringApplication.run(SpringIocTest01Application.class, args);

        log.info("项目启动成功");
    }

}
