package com.itheima.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 使用Jedis操作Redis
 */
public class JedisTest {


    @Test
    public void testRedis() {

//        1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
//        2.执行具体操作
        jedis.set("username", "xiaoming");
        String username = jedis.get("username");
        System.out.println(username);
//        jedis.del("username");
        jedis.hset("myhash", "addr", "bj");
        String hget = jedis.hget("myhash", "addr");
        System.out.println(hget);

        Set<String> keys = jedis.keys("*");

        for (String key : keys) {
            System.out.println(key);
        }
//        3.关闭连接
        jedis.close();

    }


}
