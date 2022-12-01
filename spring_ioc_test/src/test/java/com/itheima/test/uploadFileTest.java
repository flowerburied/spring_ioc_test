package com.itheima.test;

import org.junit.jupiter.api.Test;

//java测试方法

public class uploadFileTest {

    @Test
    public void test1() {
        String filename = "ererwe.jpg";
        String suffix = filename.substring(filename.lastIndexOf("."));
        System.out.println(suffix);

    }
}
