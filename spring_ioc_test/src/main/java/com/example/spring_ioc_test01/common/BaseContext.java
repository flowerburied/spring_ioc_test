package com.example.spring_ioc_test01.common;


/**
 * 基于ThreadLocal封装工具类,用户保存和获取当前登录用户id  id类型为Long
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取值
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
