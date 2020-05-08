package com.luttece.luttece.service.iml;

import com.luttece.luttece.po.User;
import com.luttece.luttece.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Log
public class UserserviceIml implements UserService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, User> opsForHash;

    @Override
    public String getString(String key) {
        System.out.println("大牛：" + redisTemplate);

        if (redisTemplate.hasKey(key)) {
            log.info("redis 查询");
            return (String) redisTemplate.opsForValue().get(key);
        } else {
            // 没有
            String val = "lettuce";
            log.info("来自mysql 查询");
            redisTemplate.opsForValue().set(key, val);
            return val;
        }
    }

    @Override
    public User selectById(String key) {
        if (opsForHash.hasKey("user", key)) {
            log.info("redis 查询");
            return opsForHash.get("user", key);
        } else {
            log.info("chaxuan mysql");
            User u = new User();
            u.setId(key);
            u.setName("zs" + key);
            u.setAge(90);
            opsForHash.put("user", key, u);
            return u;
        }

    }
}
