package com.example.spring_ioc_test01;


import com.example.spring_ioc_test01.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataRedisTest {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 操作String类型数据
     */
    @Test
    public void testString() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("city", "beijing");

        String city = (String) valueOperations.get("city");
        System.out.println(city);
        valueOperations.set("key1", "valye1", 10l, TimeUnit.SECONDS);

        Boolean aBoolean = valueOperations.setIfAbsent("city", "nanjing");
        System.out.println(aBoolean);
    }

    /**
     * 操作hash类型数据
     */

    @Test
    public void testHash() {
        HashOperations hashOperations = redisTemplate.opsForHash();
//        存值
        hashOperations.put("002", "name", "xiaoming");
        hashOperations.put("002", "age", "20");
        hashOperations.put("002", "address", "bj");

//        取值
        String age = (String) hashOperations.get("002", "age");
        System.out.println(age);

//        获取hash结构中的所有数据
        Set keys = hashOperations.keys("002");
        for (Object key : keys) {
            System.out.println(key);
        }

//        获取hash结构中所有的值
        List values = hashOperations.values("002");
        for (Object value : values) {
            System.out.println(value);
        }

    }


    /**
     * 操作list类型数据
     */
    @Test
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();

//    存值
        listOperations.leftPush("mylist", "a");
        listOperations.leftPushAll("mylist", "b", "c", "d");

//        取值
        List<String> mylist = listOperations.range("mylist", 0, -1);
        for (String value : mylist) {
            System.out.println(value);
        }

//        获得列表长度
        Long size = listOperations.size("mylist");

        int lSize = size.intValue();

        for (int i = 0; i < lSize; i++) {
            //        出队列
            String element = (String) listOperations.rightPop("mylist");
            System.out.println(element);

        }


    }


}
