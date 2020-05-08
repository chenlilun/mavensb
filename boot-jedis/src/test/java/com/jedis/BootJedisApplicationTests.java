package com.jedis;

import com.jedis.service.iml.UserserviceIml;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class BootJedisApplicationTests {
    @Autowired
    private UserserviceIml userserviceIml;
    @Test
    void contextLoads() {
    }
    @Test
    void  t1(){
            userserviceIml.getString("abc") ;
    }

}
