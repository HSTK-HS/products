package com.example.products;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    @Ignore
    public void contextLoads() {
    }

    @Test
    @Ignore
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("ss","搜索");
        stringRedisTemplate.opsForValue().set("key","value");
        String a = stringRedisTemplate.opsForValue().get("ss");
        System.out.println(stringRedisTemplate.opsForValue().get("key"));
        System.out.println(a);
    }

}
