package com.example.spring_ioc_test01.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

//全局异常处理
//捕获 RestController.class, Controller.class 方法
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
//npdp
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {

        log.error("异常信息：{}", ex.getMessage());
//        获取异常信息中 含有 "Duplicate entry"
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }

//未知错误
        return R.error("系统繁忙，请稍后重试！");
    }

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {

        log.error("异常信息：{}", ex.getMessage());


        return R.error(ex.getMessage());
    }

}
