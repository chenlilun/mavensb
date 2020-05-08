package com.jedis.service.iml;

import com.jedis.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

@Service
@Log
public class UserserviceIml implements UserService {
    @Autowired
    private JedisPool jedisPool; // jedis 连接池

    @Override
    public String getString(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                log.info("redis有数据");
                return jedis.get(key);
            } else {
                String val = "来自数据库";
                log.info("来自数据库");
                jedis.set(key, val);
                return val;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();

            }
        }

    }
}
